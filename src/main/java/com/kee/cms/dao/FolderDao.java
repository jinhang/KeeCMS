package com.kee.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kee.cms.constant.FolderConstant;
import com.kee.cms.constant.FolderConstant.Status;
import com.kee.cms.entity.Folder;
import com.kee.cms.entity.vo.FolderVo;

/**
 * 目录服务
 * 
 * @author keehang
 * 
 */

@Repository
public interface FolderDao {


	/**
	 * 增加目录
	 * 
	 * @return Integer
	 */
	public int addFolder(Folder folder);

	/**
	 * 删除目录
	 * 
	 * @param folder
	 * @return boolean
	 */
	public boolean deleteFolder(@Param("folderId") long folderId);

	
	/**
	 * 更新目录
	 * 
	 * @param folder
	 * @return Integer
	 */
	public int updateFolder(Folder folder);

	/**
	 * @param folderId
	 * @param name
	 * @param ename
	 * @param content
	 * @param status
	 */
	public void updateFolder(@Param("folderId") long folderId,
			@Param("name") String name, @Param("ename") String ename,
			@Param("content") String content);

	public int updateSort(@Param("folderId") long folderId,
			@Param("sort") int sort);

	public int updatePath(@Param("folderId") long folderId,
			@Param("path") String path);

	public int updateCount(@Param("folderId") long folderId,
			@Param("count") int count);

	// ///////////////////////////////
	// ///// 查询 ////////
	// ///////////////////////////////
	/**
	 * 得到目录
	 * 
	 * @param folderId
	 * @return Folder
	 */
	public FolderVo getFolderById(@Param("folderId") long folderId);

	/**
	 * 得到所有子目录
	 * 
	 * @param fatherId
	 * @return List<FolderVo>
	 */
	public List<FolderVo> getFolderListByFatherId(
			@Param("fatherId") long fatherId,
			@Param("status") FolderConstant.Status status);

	/**
	 * 得到folder的所有path
	 * 
	 */
	public List<String> getAllFolderPath();

	/**
	 * 通过ename获得指定目录
	 * 
	 * @param ename
	 * @return Folder
	 */
	@Deprecated
	public Folder getFolderByEname(String ename);

	/**
	 * 通过ename和fatherId获得指定目录
	 * 
	 * @param ename
	 * @param fatherId
	 * @return
	 */
	public FolderVo getFolderByEnameAndFatherId(@Param("ename") String ename,
			@Param("fatherId") long fatherId);

	/**
	 * @param folderId
	 * @param type
	 */
	public void updateType(@Param("folderId")  long folderId, @Param("type")  FolderConstant.Type type);

	/**
	 * @param folderId
	 * @param status
	 */
	public void updateStatus(@Param("folderId")   long folderId, @Param("status")  Status status);

}
