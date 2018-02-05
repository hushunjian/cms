package com.m2m.controller;

import com.m2m.copier.TagCopier;
import com.m2m.domain.TopicTag;
import com.m2m.entity.*;
import com.m2m.exception.SystemException;
import com.m2m.exception.TagAlreadyExistsException;
import com.m2m.request.*;
import com.m2m.response.*;
import com.m2m.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/tag")
public class TagController extends BaseController {
    @Autowired
    private TagService tagService;

    @ResponseBody
    @RequestMapping(value = "getAllTags")
    public Object getAllTags(@NotNull @RequestParam String token,
                             @NotNull @RequestParam Long pageIndex,
                             @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsTag extCmsTag = tagService.getAllTags(pageIndex, pageSize);
        CmsTag4GetAllTagsResponse cmsTag4GetAllTagsResponse = TagCopier.INSTANCE.asCmsTag4GetAllTagsResponse(extCmsTag);
        return success(cmsTag4GetAllTagsResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAllParentTags")
    public Object getAllParentTags(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtParentTag> extCmsAllParentTag = tagService.getAllParentTags();
        List<CmsTag4GetParentTagResponse> cmsTag4GetParentTagResponse = TagCopier.INSTANCE.asCmsTag4GetParentTagResponse(extCmsAllParentTag);
        return success(cmsTag4GetParentTagResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAllTagKingdom")
    public Object getAllTagKingdom(@NotNull @RequestParam String token,
                                   @NotNull @RequestParam Integer tagId,
                                   @NotNull @RequestParam Long pageIndex,
                                   @NotNull @RequestParam Long pageSize) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        ExtCmsAllTagKingdom ExtCmsAllTagKingdomInfo = tagService.getAllTagKingdom(tagId, pageIndex, pageSize);
        CmsTag4GetAllTagKingdomResponse cmsTag4GetAllTagKingdomResponse = TagCopier.INSTANCE.asCmsTag4GetAllTagKingdomResponse(ExtCmsAllTagKingdomInfo);
        return success(cmsTag4GetAllTagKingdomResponse);
    }

    @ResponseBody
    @RequestMapping(value = "getAllUserHobbies")
    public Object getAllUserHobbies(@NotNull @RequestParam String token) throws SystemException {
        CmsCredentialResponse credential = validate(token);
        List<ExtUserHobby> ExtCmsAllUserHobbies = tagService.getAllUserHobbies();
        List<CmsTag4GetUserHobbyResponse> cmsTag4GetUserHobbyResponse = TagCopier.INSTANCE.asCmsTag4GetUserHobbyResponse(ExtCmsAllUserHobbies);
        return success(cmsTag4GetUserHobbyResponse);
    }

    @ResponseBody
    @RequestMapping(value = "addTag", method = RequestMethod.POST)
    public Object addTag(@Validated @RequestBody CmsTag4AddTagRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        TopicTag topicTag = TagCopier.INSTANCE.asTopicTag(request);
        List<TopicTag> list = tagService.checkTagExist(topicTag);
        if (list.size() > 0) {
            throw new TagAlreadyExistsException();
        } else {
            tagService.addTag(topicTag);
            return success();
        }
    }

    @ResponseBody
    @RequestMapping(value = "addTagTopic", method = RequestMethod.POST)
    public Object addTagTopic(@Validated @RequestBody CmsTag4AddTagTopicRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        tagService.addTagTopic(request.getTopicId(), request.getTagId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "deleteTag", method = RequestMethod.POST)
    public Object deleteTag(@Validated @RequestBody CmsTag4DeleteTagRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        tagService.deleteTag(request.getTagId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "getTagSearch", method = RequestMethod.POST)
    public Object getTagSearch(@Validated @RequestBody CmsTag4GetTagSearchRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        ExtTagSearchQuery extTagSearchQuery = TagCopier.INSTANCE.asExtTagSearchQuery(request);
        ExtCmsTag extCmsTag = tagService.getTagSearch(extTagSearchQuery);
        CmsTag4GetAllTagsResponse cmsTag4GetAllTagsResponse = TagCopier.INSTANCE.asCmsTag4GetAllTagsResponse(extCmsTag);
        return success(cmsTag4GetAllTagsResponse);
    }

    @ResponseBody
    @RequestMapping(value = "removeTagTopic", method = RequestMethod.POST)
    public Object removeTagTopic(@Validated @RequestBody CmsTag4RemoveTagTopicRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        tagService.removeTagTopic(request.getTagId(), request.getTopicId());
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "updateTag", method = RequestMethod.POST)
    public Object updateTag(@Validated @RequestBody CmsTag4UpdateTagRequest request) throws SystemException {
        CmsCredentialResponse credential = validate(request.getToken());
        TopicTag topicTag = TagCopier.INSTANCE.asTopicTag(request);
        tagService.updateTag(topicTag);
        return success();
    }
}
