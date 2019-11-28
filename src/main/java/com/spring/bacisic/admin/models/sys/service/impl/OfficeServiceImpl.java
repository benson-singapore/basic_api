package com.spring.bacisic.admin.models.sys.service.impl;

import com.spring.bacisic.admin.models.sys.entity.Office;
import com.spring.bacisic.admin.models.sys.mapper.OfficeDao;
import com.spring.bacisic.admin.models.sys.service.IOfficeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职位表 服务实现类
 * </p>
 *
 * @author zhangbiyu
 * @since 2019-11-26
 */
@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeDao, Office> implements IOfficeService {

}
