package com.krtc.practice.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.krtc.core.dao.DataSourceDao;
import com.krtc.practice.bo.HAD02BO;
import com.krtc.practice.vo.HAD02VO;
import com.krtc.practice.vo.HAD02VOExample;

@Repository
public class PRA001DAO {
	public static final String VO_NAMESPACE = "com.krtc.practice.vo.mapper.HAD02VOMapper";
	
	@Resource(name = "oltp.DataSourceDao")
	private DataSourceDao dataSourceDAO;
	
	public HAD02VO findByKey(HAD02VO had02vo) {
		return this.dataSourceDAO.selectOne(VO_NAMESPACE + ".selectByPrimaryKey", had02vo);
	}

	public List<HAD02VO> selectList(HAD02VOExample had02VOExmple) {
		return this.dataSourceDAO.selectList(VO_NAMESPACE + ".selectByExample", had02VOExmple, 0, Integer.MAX_VALUE);
	}
	
	
//	public List<HAD02VO> listByKey(HAD02VO had02VO, Integer offset, Integer limit) {
//		HAD02VOExample had02VOExample = new HAD02VOExample();
//		
//		return this.dataSourceDAO.selectList(VO_NAMESPACE + ".selectByExample", had02VOExample, offset, limit);
//	}
    public List<HAD02VO> listByKey(HAD02VO had02vo, Integer offset, Integer limit) {
        HAD02VOExample had02VOExample = new HAD02VOExample();
   
        return this.dataSourceDAO.selectList(VO_NAMESPACE + ".selectByExample", had02VOExample, offset, limit);
    }
	
    public Long countList(HAD02BO had02BO) {
        HAD02VOExample had02VOExample = new HAD02VOExample();
        HAD02VOExample.Criteria criteria = had02VOExample.createCriteria();

        this.makeCriteria(criteria, had02BO);

        return this.dataSourceDAO.selectOne(VO_NAMESPACE + ".countByExample", had02VOExample);
    }
    
	public int insert(HAD02VO had02VO) {
		return this.dataSourceDAO.insert(VO_NAMESPACE + ".insert", had02VO);
	}
	
	public int update(HAD02VO had02VO) {
		return this.dataSourceDAO.update(VO_NAMESPACE + ".updateByPrimaryKeySelective", had02VO);
	}
	
	public int delete(HAD02VO had02VO) {
		return this.dataSourceDAO.delete(VO_NAMESPACE + ".deleteByPrimaryKey", had02VO);
	}
	
	public HAD02VOExample.Criteria makeCriteria(HAD02VOExample.Criteria criteria, HAD02BO had02BO){
		
		return criteria;
	}
} 
