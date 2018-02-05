package com.m2m.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m2m.copier.KingdomCopier;
import com.m2m.entity.ExcelData;
import com.m2m.entity.ExtCmsKingdom;
import com.m2m.entity.ExtCmsKingdomListed;
import com.m2m.entity.ExtCmsKingdomReview;
import com.m2m.entity.ExtCmsKingdomValue;
import com.m2m.entity.ExtCmsLottery;
import com.m2m.entity.ExtCmsLotteryUser;
import com.m2m.entity.ExtKingdomQuery;
import com.m2m.entity.ExtKingdomRestTime;
import com.m2m.entity.ExtKingdomReview;
import com.m2m.entity.ExtKingdomUserQuery;
import com.m2m.exception.NoExportDataException;
import com.m2m.exception.SystemException;
import com.m2m.request.CmsKingdom4ExportKingdomUsersRequest;
import com.m2m.request.CmsKingdom4GetKingdomSearchRequest;
import com.m2m.request.CmsKingdom4GetKingdomUserSearchRequest;
import com.m2m.request.CmsKingdom4GetKingdomValueSearchRequest;
import com.m2m.request.CmsKingdom4GetListedKingdomSearchRequest;
import com.m2m.request.CmsKingdom4GetLotteryUserSearchRequest;
import com.m2m.request.CmsKingdom4KingdomTransactionRequest;
import com.m2m.request.CmsKingdom4SetKingdomRestTimeRequest;
import com.m2m.request.CmsKingdom4SetListedKingdomStatusRequest;
import com.m2m.request.CmsKingdom4SetLotteryAppointRequest;
import com.m2m.request.CmsKingdom4UpdateKingRequest;
import com.m2m.response.CmsCredentialResponse;
import com.m2m.response.CmsKindom4GetKingdomValueResponse;
import com.m2m.response.CmsKingdom4AllKingdomResponse;
import com.m2m.response.CmsKingdom4AllLotteryResponse;
import com.m2m.response.CmsKingdom4GetAllListedKingdomResponse;
import com.m2m.response.CmsKingdom4GetKingdomUserResponse;
import com.m2m.response.CmsKingdomGetLotteryUsersResponse;
import com.m2m.service.KingdomService;
import com.m2m.util.ExportExcelUtils;

@RestController
@Transactional
@RequestMapping("/api/kingdom")
public class KingdomController extends BaseController {

    @Autowired
    private KingdomService kingdomService;

    @ResponseBody
    @RequestMapping(value = "getAllKingdoms")
    public Object getAllKingdoms(@NotNull @RequestParam String token,
                                 @NotNull @RequestParam Long pageIndex,
                                 @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsKingdom extCmsKingdoms = kingdomService.getAllKingdomsInfoByPage(pageIndex, pageSize);
        CmsKingdom4AllKingdomResponse cmsKingdom4AllKingdomResponse = KingdomCopier.INSTANCE.asCmsKingdom4AllKingdomsResponses(extCmsKingdoms);
        return success(cmsKingdom4AllKingdomResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAllLotteries")
    public Object getAllLotteries(@NotNull @RequestParam String token,
                                  @NotNull @RequestParam Long pageIndex,
                                  @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsLottery extCmsLotteries = kingdomService.getAllLotteriesByPage(pageIndex, pageSize);
        CmsKingdom4AllLotteryResponse cmsKingdom4AllLotteryResponse = KingdomCopier.INSTANCE.asCmsKingdom4AllLotteryResponse(extCmsLotteries);
        return success(cmsKingdom4AllLotteryResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getKingdomSearch", method = RequestMethod.POST)
    public Object getKingdomSearch(@Validated @RequestBody CmsKingdom4GetKingdomSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtKingdomQuery extKingdomQuery = KingdomCopier.INSTANCE.asExtKingdomInfoQuery(request);
        ExtCmsKingdom extCmsKingdomByCondition = kingdomService.getKingdomSearch(extKingdomQuery);
        CmsKingdom4AllKingdomResponse cmsKingdom4AllKingdomResponse = KingdomCopier.INSTANCE.asCmsKingdom4AllKingdomsResponses(extCmsKingdomByCondition);
        return success(cmsKingdom4AllKingdomResponse);
    }


    @ResponseBody
    @RequestMapping(value = "getLotterySearch")
    public Object getLotterySearch(@NotNull @RequestParam String token,
                                   @NotNull @RequestParam Long pageIndex,
                                   @NotNull @RequestParam Long pageSize,
                                   @NotNull @RequestParam String topicName,
                                   @NotNull @RequestParam String lotteryName) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsLottery extCmsLotteryByCondition = kingdomService.getLotterySearch(pageIndex, pageSize, topicName, lotteryName);
        CmsKingdom4AllLotteryResponse cmsKingdom4AllLotteryResponse = KingdomCopier.INSTANCE.asCmsKingdom4AllLotteryResponse(extCmsLotteryByCondition);
        return success(cmsKingdom4AllLotteryResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getLotteryUsers")
    public Object getLotteryUsers(@NotNull @RequestParam String token,
                                  @NotNull @RequestParam Long pageIndex,
                                  @NotNull @RequestParam Long pageSize,
                                  @NotNull @RequestParam Integer lotteryId) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsLotteryUser extCmsLotteryUser = kingdomService.getLotteryUsers(pageIndex, pageSize, lotteryId);
        CmsKingdomGetLotteryUsersResponse cmsKingdomGetLotteryUsersResponse = KingdomCopier.INSTANCE.asCmsKingdomGetLotteryUsersResponse(extCmsLotteryUser);
        return success(cmsKingdomGetLotteryUsersResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getLotteryUserSearch", method = RequestMethod.POST)
    public Object getLotteryUserSearch(@Validated @RequestBody CmsKingdom4GetLotteryUserSearchRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtCmsLotteryUser extCmsLotteryUser = kingdomService.getLotteryUserSearch(request.getPageIndex(), request.getPageSize(), request.getLotteryId(), request.getUserNick(), request.getIsAppoint());
        CmsKingdomGetLotteryUsersResponse cmsKingdomGetLotteryUsersResponse = KingdomCopier.INSTANCE.asCmsKingdomGetLotteryUsersResponse(extCmsLotteryUser);
        return success(cmsKingdomGetLotteryUsersResponse);
    }

    @ResponseBody
    @RequestMapping(value = "setLotteryAppoint", method = RequestMethod.POST)
    public Object setLotteryAppoint(@Validated @RequestBody CmsKingdom4SetLotteryAppointRequest request)
            throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        kingdomService.setLotteryAppoint(request.getLotteryId(), request.getUserId(), request.getIsAppoint());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "refreshCache")
    public Object refreshCache(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        kingdomService.refreshCache();
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "getKingdomValues")
    public Object getKingdomValues(@NotNull @RequestParam String token,
                                   @NotNull @RequestParam Long pageIndex,
                                   @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsKingdomValue extCmsKingdomValue = kingdomService.getKingdomValues(pageIndex, pageSize);
        CmsKindom4GetKingdomValueResponse cmsKindom4GetKingdomValueResponse = KingdomCopier.INSTANCE.asCmsKindom4GetKingdomValueResponse(extCmsKingdomValue);
        return success(cmsKindom4GetKingdomValueResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getKingdomValueSearch")
    public Object getKingdomValueSearch(@Validated @RequestBody CmsKingdom4GetKingdomValueSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtCmsKingdomValue extCmsKingdomValue = kingdomService.getKingdomValueSearch(request.getPageIndex(), request.getPageSize(), request.getTopicName(), request.getOrderParam(), request.getOrder());
        CmsKindom4GetKingdomValueResponse cmsKindom4GetKingdomValueResponse = KingdomCopier.INSTANCE.asCmsKindom4GetKingdomValueResponse(extCmsKingdomValue);
        return success(cmsKindom4GetKingdomValueResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getKingdomUsers")
    public Object getKingdomUsers(@NotNull @RequestParam String token,
                                  @NotNull @RequestParam Long pageIndex,
                                  @NotNull @RequestParam Long pageSize,
                                  @NotNull @RequestParam Long topicId) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsKingdomReview extCmsKingdomReview = kingdomService.getKingdomUsers(pageIndex, pageSize, topicId);
        CmsKingdom4GetKingdomUserResponse cmsKingdom4GetKingdomUserResponse = KingdomCopier.INSTANCE.asCmsKingdom4GetKingdomUserResponse(extCmsKingdomReview);
        return success(cmsKingdom4GetKingdomUserResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getKingdomUserSearch", method = RequestMethod.POST)
    public Object getKingdomUserSearch(@Validated @RequestBody CmsKingdom4GetKingdomUserSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtKingdomUserQuery extKingdomUserQuery = KingdomCopier.INSTANCE.asExtKingdomUserQuery(request);
        ExtCmsKingdomReview extCmsKingdomReview = kingdomService.getKingdomUserSearch(extKingdomUserQuery);
        CmsKingdom4GetKingdomUserResponse cmsKingdom4GetKingdomUserResponse = KingdomCopier.INSTANCE.asCmsKingdom4GetKingdomUserResponse(extCmsKingdomReview);
        return success(cmsKingdom4GetKingdomUserResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getListedKingdoms")
    public Object getListedKingdoms(@NotNull @RequestParam String token,
                                    @NotNull @RequestParam Long pageIndex,
                                    @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsKingdomListed extCmsKingdomListed = kingdomService.getListedKingdoms(pageIndex, pageSize);
        CmsKingdom4GetAllListedKingdomResponse cmsKingdom4GetAllListedKingdomResponse = KingdomCopier.INSTANCE.asCmsKingdom4GetAllListedKingdomResponse(extCmsKingdomListed);
        return success(cmsKingdom4GetAllListedKingdomResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getListedKingdomSearch")
    public Object getListedKingdomSearch(@Validated @RequestBody CmsKingdom4GetListedKingdomSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtCmsKingdomListed extCmsKingdomListed = kingdomService.getListedKingdomSearch(request.getTopicName(), request.getStatus(), request.getPageIndex(), request.getPageSize());
        CmsKingdom4GetAllListedKingdomResponse cmsKingdom4GetAllListedKingdomResponse = KingdomCopier.INSTANCE.asCmsKingdom4GetAllListedKingdomResponse(extCmsKingdomListed);
        return success(cmsKingdom4GetAllListedKingdomResponse);
    }

    @ResponseBody
    @RequestMapping(value = "setKingdomRestTime", method = RequestMethod.POST)
    public Object setKingdomRestTime(@Validated @RequestBody CmsKingdom4SetKingdomRestTimeRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        String startAt = new SimpleDateFormat("yyyy-MM-dd").format(request.getStartAt());
        String endAt = new SimpleDateFormat("yyyy-MM-dd").format(request.getEndAt());
        kingdomService.setKingdomRestTime(startAt, endAt);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updateKing", method = RequestMethod.POST)
    public Object updateKing(@Validated @RequestBody CmsKingdom4UpdateKingRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        kingdomService.updateKing(request.getTopicId(), request.getSourceUid(), request.getTargetUid(), request.getOptUid());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "setListedKingdomStatus", method = RequestMethod.POST)
    public Object setListedKingdomStatus(@Validated @RequestBody CmsKingdom4SetListedKingdomStatusRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        kingdomService.setListedKingdomStatus(request.getId(), request.getStatus());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "kingdomTransaction", method = RequestMethod.POST)
    public Object kingdomTransaction(@Validated @RequestBody CmsKingdom4KingdomTransactionRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        kingdomService.kingdomTransaction(request.getId(), request.getMeNumber());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "getPendingListedKingdoms")
    public Object getPendingListedKingdoms(@NotNull @RequestParam String token,
                                           @NotNull @RequestParam Long pageIndex,
                                           @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsKingdomListed extCmsKingdomListed = kingdomService.getPendingListedKingdoms(pageIndex, pageSize);
        CmsKingdom4GetAllListedKingdomResponse cmsKingdom4GetAllListedKingdomResponse = KingdomCopier.INSTANCE.asCmsKingdom4GetAllListedKingdomResponse(extCmsKingdomListed);
        return success(cmsKingdom4GetAllListedKingdomResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getPendingListedKingdomSearch")
    public Object getPendingListedKingdomSearch(@NotNull @RequestParam String token,
                                                @NotNull @RequestParam Long pageIndex,
                                                @NotNull @RequestParam Long pageSize,
                                                @NotNull @RequestParam String topicName) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsKingdomListed extCmsKingdomListed = kingdomService.getPendingListedKingdomSearch(topicName, pageIndex, pageSize);
        CmsKingdom4GetAllListedKingdomResponse cmsKingdom4GetAllListedKingdomResponse = KingdomCopier.INSTANCE.asCmsKingdom4GetAllListedKingdomResponse(extCmsKingdomListed);
        return success(cmsKingdom4GetAllListedKingdomResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getKingdomRestTime")
    public Object getKingdomRestTime(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtKingdomRestTime extKingdomRestTime = kingdomService.getKingdomRestTime();
        return success(extKingdomRestTime);
    }
    
    @ResponseBody
    @RequestMapping(value = "exportKingdomUsers")
    public Object exportKingdomUsers(HttpServletResponse response,CmsKingdom4ExportKingdomUsersRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtKingdomUserQuery extKingdomUserQuery = KingdomCopier.INSTANCE.asExtKingdomUserQuery(request);
        List<ExtKingdomReview> extKingdomReviews = kingdomService.exportKingdomUsers(extKingdomUserQuery);
        if(extKingdomReviews.size()>0){
        	ExcelData data = new ExcelData();
        	List<Object> beans = new ArrayList<Object>();
        	for(ExtKingdomReview extKingdomReview : extKingdomReviews){
        		beans.add(extKingdomReview);
        	}
        	data = ExportExcelUtils.setExcelData(beans);
        	ExportExcelUtils.exportExcel(response,"exportExcel.xlsx",data);
        }else{
        	throw new NoExportDataException();
        }
        return success();
    }
}
