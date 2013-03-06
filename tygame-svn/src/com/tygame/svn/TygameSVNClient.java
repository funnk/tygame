package com.tygame.svn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * SVN客户端，用来处理SVN请求
 * 
 * @Package com.tygame.svn
 * @FileName TygameSVNClient.java
 * @Author TyGame
 * @Date 2013-1-6
 */
public class TygameSVNClient {
	public static final int INIT_TYPE_HTTP = 0;
	public static final int INIT_TYPE_SVN = 1;
	public static final int INIT_TYPE_FILE = 2;

	private TygameSVNClient(int initType) {
		switch (initType) {
		case INIT_TYPE_HTTP:
			DAVRepositoryFactory.setup();
			break;
		case INIT_TYPE_SVN:
			SVNRepositoryFactoryImpl.setup();
			break;
		case INIT_TYPE_FILE:
			FSRepositoryFactory.setup();
			break;
		}
	}

	public static TygameSVNClient getInstance(int initType) {
		return new TygameSVNClient(initType);
	}

	/**
	 * 执行check out操作
	 * 
	 * @throws SVNException
	 */
	public long doCheckOut(String svnURL, String localPath, String userName, String password) throws SVNException {
		SVNURL repositoryURL = SVNURL.parseURIEncoded(svnURL);
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
		// 实例化客户端管理类
		SVNClientManager ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
		// 要把版本库的内容check out到的目录
		File wcDir = new File(localPath);
		// 通过客户端管理类获得updateClient类的实例。
		SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
		updateClient.setIgnoreExternals(false);
		// 执行check out 操作，返回工作副本的版本号。
		return updateClient.doCheckout(repositoryURL, wcDir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, false);
	}

	/**
	 * 执行commit操作
	 * 
	 * @throws SVNException
	 */
	public SVNCommitInfo doCommit(String localFile, String commitMessage, String userName, String password) throws SVNException {
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
		// 实例化客户端管理类
		SVNClientManager ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
		// 要提交的文件
		File commitFile = new File(localFile);
		// 获取此文件的状态（是文件做了修改还是新添加的文件？）
		SVNStatus status = ourClientManager.getStatusClient().doStatus(commitFile, false);
		// 如果此文件是新增加的则先把此文件添加到版本库，然后提交。
		if (status.getContentsStatus() == SVNStatusType.STATUS_NONE) {
			// 把此文件增加到版本库中
			ourClientManager.getWCClient().doAdd(commitFile, false, false, false, SVNDepth.INFINITY, false, false);
			// 提交此文件
			return ourClientManager.getCommitClient().doCommit(new File[] { commitFile }, true, commitMessage, null, null, true, false, SVNDepth.INFINITY);
		}
		// 如果此文件不是新增加的，直接提交。
		else {
			return ourClientManager.getCommitClient().doCommit(new File[] { commitFile }, true, commitMessage, null, null, true, false, SVNDepth.INFINITY);
		}
	}

	/**
	 * 执行commit操作多文件
	 * 
	 * @throws SVNException
	 */
	public SVNCommitInfo doCommit(String[] localFiles, String commitMessage, String userName, String password) throws SVNException {
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
		// 实例化客户端管理类
		SVNClientManager ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
		// 要提交的文件
		List<File> fileList = new ArrayList<File>();
		for (String fileName : localFiles) {
			fileList.add(new File(fileName));
		}
		//将没有添加到版本库的文件添加到版本库中
		for (File file : fileList) {
			if (isVersioned(file.getPath(), userName, password)) {
				ourClientManager.getWCClient().doAdd(file, false, false, false, SVNDepth.INFINITY, false, false);
			}
		}
		
		return ourClientManager.getCommitClient().doCommit(fileList.toArray(new File[fileList.size()]), true, commitMessage, null, null, true, false, SVNDepth.INFINITY);
	}

	/**
	 * 执行import操作
	 * 
	 * @throws SVNException
	 */
	public SVNCommitInfo doImport(String svnURL, String localPath, String userName, String password) throws SVNException {
		SVNURL repositoryURL = SVNURL.parseURIEncoded(svnURL);
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
		// 实例化客户端管理类
		SVNClientManager ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
		// 要把此目录中的内容导入到版本库
		File impDir = new File(localPath);
		// 执行导入操作
		return ourClientManager.getCommitClient().doImport(impDir, repositoryURL, "import operation!", null, false, false, SVNDepth.INFINITY);
	}

	/**
	 * 执行update操作
	 * 
	 * @throws SVNException
	 */
	public long doUpdate(String localPath, String userName, String password) throws SVNException {
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
		// 实例化客户端管理类
		SVNClientManager ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
		// 要更新的文件
		File updateFile = new File(localPath);
		// 获得updateClient的实例
		SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
		updateClient.setIgnoreExternals(false);
		// 执行更新操作
		return updateClient.doUpdate(updateFile, SVNRevision.HEAD, SVNDepth.INFINITY, false, false);
	}

	/**
	 * 判断本地文件是否已经加入到版本控制中
	 */
	public boolean isVersioned(String localFile, String userName, String password) throws SVNException {
		SVNStatus status = getStatus(localFile, userName, password);
		return status.isVersioned();
	}

	/**
	 * 判断本地文件是否为Modify状态
	 */
	public boolean isModify(String localFile, String userName, String password) throws SVNException {
		SVNStatus status = getStatus(localFile, userName, password);
		if (status.isVersioned()) {
			return status.getContentsStatus() == SVNStatusType.STATUS_MODIFIED;
		} else {
			return false;
		}
	}

	/**
	 * 判断本地文件是否为ADD状态
	 */
	public boolean isAdd(String localFile, String userName, String password) throws SVNException {
		SVNStatus status = getStatus(localFile, userName, password);
		return status.getContentsStatus() == SVNStatusType.STATUS_ADDED;
	}

	/**
	 * 判断本地文件是否为DELETE状态
	 */
	public boolean isDelete(String localFile, String userName, String password) throws SVNException {
		SVNStatus status = getStatus(localFile, userName, password);
		return status.getContentsStatus() == SVNStatusType.STATUS_DELETED;
	}

	/**
	 * 判断本地文件是否为Merged状态
	 */
	public boolean isMerged(String localFile, String userName, String password) throws SVNException {
		SVNStatus status = getStatus(localFile, userName, password);
		return status.getContentsStatus() == SVNStatusType.MERGED;
	}

	/**
	 * 获取本地文件状态
	 * 
	 * @throws SVNException
	 */
	private SVNStatus getStatus(String localFile, String userName, String password) throws SVNException {
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
		// 实例化客户端管理类
		SVNClientManager ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
		// 要提交的文件
		File commitFile = new File(localFile);
		// 获取此文件的状态（是文件做了修改还是新添加的文件？）
		return ourClientManager.getStatusClient().doStatus(commitFile, false);
	}
}
