package com.m2m.service;

import com.m2m.Constant;
import com.m2m.domain.AppConfig;
import com.m2m.domain.AppConfigExample;
import com.m2m.domain.AppLightboxSource;
import com.m2m.domain.AppLightboxSourceExample;
import com.m2m.entity.ExtCmsBasicConfig;
import com.m2m.mapper.AppConfigMapper;
import com.m2m.mapper.AppLightboxSourceMapper;
import com.m2m.mapper.ExtAppConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppConfigService extends BaseService {
    @Autowired
    private AppConfigMapper appConfigMapper;

    @Autowired
    private AppLightboxSourceMapper appLightboxSourceMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ExtAppConfigMapper extAppConfigMapper;

    public List<ExtCmsBasicConfig> getAppBasicConfig() {
        List<ExtCmsBasicConfig> extCmsBasicConfigs = new ArrayList<>();
        List<String> appConfigNames = extAppConfigMapper.getConfigName();
        for (int i = 0; i < appConfigNames.size(); i++) {
            ExtCmsBasicConfig extCmsBasicConfig = new ExtCmsBasicConfig();
            extCmsBasicConfig.setName(appConfigNames.get(i));
            extCmsBasicConfig.setGroupId(i + 1);
            List<ExtCmsBasicConfig.Item> items = new ArrayList<ExtCmsBasicConfig.Item>();
            extCmsBasicConfig.setItems(items);
            extCmsBasicConfigs.add(extCmsBasicConfig);
        }
        List<AppConfig> appConfigs = appConfigMapper.selectByExample(new AppConfigExample());
        for (AppConfig appConfig : appConfigs) {
            int index = -1;
            for (int i = 0; i < appConfigNames.size(); i++) {
                if (appConfigNames.get(i).equals(appConfig.getTypeName())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                ExtCmsBasicConfig extCmsBasicConfig = extCmsBasicConfigs.get(index);
                List<ExtCmsBasicConfig.Item> items = extCmsBasicConfig.getItems();
                ExtCmsBasicConfig.Item item = new ExtCmsBasicConfig.Item();
                item.setKey(appConfig.getConfigKey());
                item.setValue(appConfig.getConfigValue());
                item.setDes(appConfig.getName());
                items.add(item);
            }
        }
        return extCmsBasicConfigs;
    }

    public void refreshCache() {
        List<AppConfig> list = appConfigMapper.selectByExample(new AppConfigExample());
        if (null != list) {
            for (AppConfig c : list) {
                redisService.set(Constant.APP_CONFIG_KEY_PRE + c.getConfigKey(), c.getConfigValue());
            }
        }
    }

    public void updateAppBasicConfig(String key, String value) {
        if (key == null || key.length() == 0 || value == null) {
            return;
        }
        AppConfigExample ex = new AppConfigExample();
        ex.createCriteria().andConfigKeyEqualTo(key);
        List<AppConfig> listConfig = appConfigMapper.selectByExample(ex);
        if (listConfig.size() > 0) {
            AppConfig config = listConfig.get(0);
            config.setConfigValue(value);
            appConfigMapper.updateByPrimaryKeySelective(config);
            redisService.set(Constant.APP_CONFIG_KEY_PRE + key, value);
        }
    }

    public List<AppLightboxSource> getLightBoxList(Long pageIndex, Long pageSize) {
        AppLightboxSourceExample example = new AppLightboxSourceExample();
        if (pageIndex < 1) {
            pageIndex = 1L;
        }
        example.setOrderByClause(" start_time desc limit " + (pageIndex - 1) * pageSize + "," + pageSize);
        return appLightboxSourceMapper.selectByExample(example);
    }

    public Long getListBoxCount() {
        AppLightboxSourceExample example = new AppLightboxSourceExample();
        return appLightboxSourceMapper.countByExample(example);
    }

    public List<AppLightboxSource> getLightBoxListByTime(Date searchTime, Long pageIndex, Long pageSize) {
        AppLightboxSourceExample example = new AppLightboxSourceExample();
        AppLightboxSourceExample.Criteria criteria = example.createCriteria();
        criteria.andStartTimeLessThanOrEqualTo(searchTime);
        criteria.andEndTimeGreaterThanOrEqualTo(searchTime);
        example.setOrderByClause(" start_time desc limit " + (pageIndex - 1) * pageSize + "," + pageSize);
        return appLightboxSourceMapper.selectByExample(example);

    }

    public Long getListBoxCountByTime(Date searchTime) {
        AppLightboxSourceExample example = new AppLightboxSourceExample();
        AppLightboxSourceExample.Criteria criteria = example.createCriteria();
        criteria.andStartTimeLessThanOrEqualTo(searchTime);
        criteria.andEndTimeGreaterThanOrEqualTo(searchTime);
        return appLightboxSourceMapper.countByExample(example);
    }

    public void updateLightBox(AppLightboxSource appLightboxSource) {
        appLightboxSourceMapper.updateByPrimaryKeySelective(appLightboxSource);
    }

    public void deleteLightBox(Long id) {
        appLightboxSourceMapper.deleteByPrimaryKey(id);
    }

    public void addLightBox(AppLightboxSource appLightboxSource) {
        appLightboxSourceMapper.insertSelective(appLightboxSource);
    }


}
