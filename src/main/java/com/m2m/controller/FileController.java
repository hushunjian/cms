package com.m2m.controller;

import com.m2m.exception.SystemException;
import com.m2m.response.CmsFileResponse;
import com.m2m.service.FileTransferService;
import com.m2m.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@Transactional
@RequestMapping("/api/file")
public class FileController extends BaseController {
    @Autowired
    private FileTransferService fileTransferService;

    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public Object uploadImage(MultipartHttpServletRequest request) throws SystemException {
        MultipartFile file = request.getFile("file");
        String source = request.getSession().getId() + System.currentTimeMillis();
        String imgName = MD5.getMD5InHex(source, "1");
        fileTransferService.upload(file, imgName);
        CmsFileResponse cmsFileResponse = new CmsFileResponse();
        cmsFileResponse.setFileName(imgName);
        return success(cmsFileResponse);
    }

}
