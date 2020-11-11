package com.omg.comutting.dao;

import java.util.List;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;

public interface CommutingDao {
	
	/**
	 * ë¡ê·¸?¸? ê¸°ë³¸ row ??±
	 * @param dto
	 * @return
	 * @author ?ê´ë??
	 */
	public int doInsert(DTO dto);
	
	/**
	 * ì¶ê·¼,?´ê·? ?±ë¡?
	 * @param dto
	 * @return ?±ê³?(1), ?¤?¨(0)
	 * @author ?ê´ë??
	 */
	public int doUpdate(DTO dto);
	
	/**
	 * row ?­? 
	 * @param dto
	 * @return ?±ê³?(1), ?¤?¨(0)
	 * @author ?ê´ë??
	 */
	public int doDelete(DTO dto);
	
	/**
	 * ê¸ì¼ ë³¸ì¸ ?ê°? select
	 * @param dto
	 * @return DTO
	 * @author ?ê´ë??
	 */
	public DTO doSelectOne(DTO dto);
	
	/**
	 * ? ì§ë³ , ë¶??ë³?
	 * @param search
	 * @return List<Commuting>
	 * ?ê´ë??
	 */
	public List<Commuting> doSelectList(Search search);
	
	/**
	 * ³» ±Ù¹«ÇöÈ²
	 * @param search
	 * @return List<Commuting>
	 * ¾ç±¤¹Î
	 */
	public List<Commuting> doSelectMyList(DTO dto);
	
	/**
	 * ?´ ê·¼ë¬´??©
	 * @param search
	 * @return List<Commuting>
	 * ?ê´ë??
	 */
	public List<Commuting> doSelectMyList(DTO dto);
	
	/**
	 * ?ì¹? ê¸°ë³¸ ?¬? insert
	 * @return ?±ê³?(1), ?¤?¨(0)
	 * ?ê´ë??
	 */
	public int doInit();
	
	/**
	 * ê·¼ë¬´?ê°? update
	 * @param dto
	 * @return ?±ê³?(1), ?¤?¨(0)
	 * ?ê´ë??
	 */
	public int doUpdateWorkTime(DTO dto);
	
	
	
}
