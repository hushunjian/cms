package com.m2m.controller;

import com.m2m.Constant;
import com.m2m.copier.ListRankCopier;
import com.m2m.domain.BillBoard;
import com.m2m.domain.BillboardDetails;
import com.m2m.domain.BillboardRelation;
import com.m2m.entity.*;
import com.m2m.exception.ColourFormatErrorException;
import com.m2m.exception.SystemException;
import com.m2m.request.*;
import com.m2m.response.*;
import com.m2m.service.ListRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@Transactional
@RequestMapping("/api/listRank")
public class ListRankController extends BaseController {
    @Autowired
    private ListRankService listRankService;

    @ResponseBody
    @RequestMapping(value = "getAllListRankUsers")
    public Object getAllListRankUsers(@NotNull @RequestParam String token,
                                      @NotNull @RequestParam Long sourceId) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsRankUser extCmsRankUser = listRankService.getAllListRankUsers(sourceId);
        CmsListRank4GetAllListRankUsersResponse cmsListRank4GetAllListRankUsersResponse = ListRankCopier.INSTANCE.asCmsListRank4GetAllListRankUsersResponse(extCmsRankUser);
        return success(cmsListRank4GetAllListRankUsersResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAllListRankSets")
    public Object getAllListRankSets(@NotNull @RequestParam String token,
                                     @NotNull @RequestParam Long sourceId) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsRankSet extCmsRankSet = listRankService.getAllListRankSets(sourceId);
        CmsListRank4GetAllListRankSetsResponse cmsListRank4GetAllListRankSetsResponse = ListRankCopier.INSTANCE.asCmsListRank4GetAllListRankSetsResponse(extCmsRankSet);
        return success(cmsListRank4GetAllListRankSetsResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAllListRankKingdoms")
    public Object getAllListRankKingdoms(@NotNull @RequestParam String token,
                                         @NotNull @RequestParam Long sourceId) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsRankKingdom extCmsRankKingdom = listRankService.getAllListRankKingdoms(sourceId);
        CmsListRank4GetAllListRankKingdomsResponse cmsListRank4GetAllListRankKingdomsResponse = ListRankCopier.INSTANCE.asCmsListRank4GetAllListRankKingdomsResponse(extCmsRankKingdom);
        return success(cmsListRank4GetAllListRankKingdomsResponse);
    }

    @ResponseBody
    @RequestMapping(value = "deleteEntry", method = RequestMethod.POST)
    public Object deleteEntry(@Validated @RequestBody CmsListRank4DeleteEntryRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        listRankService.deleteEntry(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "addEntry", method = RequestMethod.POST)
    public Object addEntry(@Validated @RequestBody CmsListRank4AddEntryRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        List<BillboardRelation> billboardRelations = ListRankCopier.INSTANCE.asBillboardRelationList(request.getTargetData());
        listRankService.addEntry(billboardRelations, request.getSourceId(), request.getType());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "sortEntry", method = RequestMethod.POST)
    public Object sortEntry(@Validated @RequestBody CmsListRank4SortEntryRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        List<BillboardRelation> billboardRelations = ListRankCopier.INSTANCE.asBillboardRelationList(request.getTargetData());
        listRankService.sortEntry(billboardRelations);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "getAllListRanks")
    public Object getAllListRanks(@NotNull @RequestParam String token,
                                  @NotNull @RequestParam Long pageIndex,
                                  @NotNull @RequestParam Long pageSize,
                                  @NotNull @RequestParam Integer type) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtListRank> extListRankList = listRankService.getAllListRanks(pageIndex, pageSize, type);
        CmsListRank4GetAllListRankResponse cmsListRank4GetAllListRankResponse = new CmsListRank4GetAllListRankResponse();
        cmsListRank4GetAllListRankResponse.setData(extListRankList);
        Long total = listRankService.getAllListRankCount();
        cmsListRank4GetAllListRankResponse.setTotal(total);
        return success(cmsListRank4GetAllListRankResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAllListRankModes")
    public Object getAllListRankModes(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtBillBoardMode> extBillBoardModes = listRankService.getAllListRankModes();
        Long total = listRankService.getAllListRankModeCount();
        CmsListRank4GetAllListRankModeResponse cmsListRank4GetAllListRankModeResponse = new CmsListRank4GetAllListRankModeResponse();
        cmsListRank4GetAllListRankModeResponse.setData(extBillBoardModes);
        cmsListRank4GetAllListRankModeResponse.setTotal(total);
        return success(cmsListRank4GetAllListRankModeResponse);
    }

    @ResponseBody
    @RequestMapping(value = "addListRank", method = RequestMethod.POST)
    public Object addListRank(@Validated @RequestBody CmsListRank4AddListRankRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        if (request.getBgColor() != null) {
            if (!Pattern.matches(Constant.COLOUR_REGEX, request.getBgColor())) {
                throw new ColourFormatErrorException();
            }
        }
        BillBoard billBoard = ListRankCopier.INSTANCE.asBillBoard(request);
        listRankService.addListRank(billBoard);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updateListRank", method = RequestMethod.POST)
    public Object updateListRank(@Validated @RequestBody CmsListRank4UpdateListRankRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        if (request.getBgColor() != null) {
            if (!Pattern.matches(Constant.COLOUR_REGEX, request.getBgColor())) {
                throw new ColourFormatErrorException();
            }
        }
        BillBoard billBoard = ListRankCopier.INSTANCE.asBillBoard(request);
        listRankService.updateListRank(billBoard);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteListRank", method = RequestMethod.POST)
    public Object deleteListRank(@Validated @RequestBody CmsListRank4DeleteListRankRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        listRankService.deleteListRank(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "getAllOnlineListRanks")
    public Object getAllOnlineListRanks(@NotNull @RequestParam String token,
                                        @NotNull @RequestParam Integer type) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtOnlineListRank> onlineList = listRankService.getAllOnlineListRanksByType(type);
        CmsListRank4GetAllOnlineListRankResponse cmsListRank4GetAllOnlineListRankResponse = new CmsListRank4GetAllOnlineListRankResponse();
        cmsListRank4GetAllOnlineListRankResponse.setData(onlineList);
        return success(cmsListRank4GetAllOnlineListRankResponse);
    }

    @ResponseBody
    @RequestMapping(value = "deleteOnlineListRank", method = RequestMethod.POST)
    public Object deleteOnlineListRank(@Validated @RequestBody CmsListRank4DeleteOnlineListRankRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        listRankService.deleteOnlineListRankByIdAndType(request.getId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "addOnlineListRank", method = RequestMethod.POST)
    public Object addOnlineListRank(@Validated @RequestBody CmsListRank4AddOnlineListRankRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        BillboardDetails billboardDetails = ListRankCopier.INSTANCE.asBillboardDetails(request);
        listRankService.addOnlineListRank(billboardDetails);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "setOnlineListRank", method = RequestMethod.POST)
    public Object setOnlineListRank(@Validated @RequestBody CmsListRank4SetOnlineListRankRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        BillboardDetails billboardDetails = ListRankCopier.INSTANCE.asBillboardDetails(request);
        listRankService.setOnlineListRank(billboardDetails);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "sortOnlineListRank", method = RequestMethod.POST)
    public Object sortOnlineListRank(@Validated @RequestBody CmsListRank4SortOnlineListRankRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        List<BillboardDetails> billboardDetailsList = ListRankCopier.INSTANCE.asBillboardDetailsList(request.getTargetData());
        listRankService.sortOnlineListRank(billboardDetailsList);
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "getAppUsers")
    public Object getAppUsers(@NotNull @RequestParam String token,
                              @NotNull @RequestParam Long pageIndex,
                              @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsListRankAppUser extCmsListRankAppUser = listRankService.getAppUsers(pageIndex, pageSize);
        CmsListRank4GetAppUserResponse cmsListRank4GetAppUserResponse = ListRankCopier.INSTANCE.asCmsListRank4GetAppUserResponse(extCmsListRankAppUser);
        return success(cmsListRank4GetAppUserResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAppUserSearch", method = RequestMethod.POST)
    public Object getAppUserSearch(@Validated @RequestBody CmsListRankGetAppUserSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtListRankAppUserQuery extListRankAppUserQuery = ListRankCopier.INSTANCE.asExtListRankAppUserQuery(request);
        ExtCmsListRankAppUser extCmsListRankAppUser = listRankService.getAppUserSearch(extListRankAppUserQuery);
        CmsListRank4GetAppUserResponse cmsListRank4GetAppUserResponse = ListRankCopier.INSTANCE.asCmsListRank4GetAppUserResponse(extCmsListRankAppUser);
        return success(cmsListRank4GetAppUserResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getListRankById")
    public Object getListRankById(@NotNull @RequestParam String token,
                                  @NotNull @RequestParam Long id) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        BillBoard billBoard = listRankService.getListRankById(id);
        ExtListRank extListRank = ListRankCopier.INSTANCE.asExtListRank(billBoard);
        return success(extListRank);
    }
}
