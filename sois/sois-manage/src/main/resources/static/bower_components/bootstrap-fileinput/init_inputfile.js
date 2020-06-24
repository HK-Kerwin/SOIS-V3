/**
 * 
 */

 	// 初始化上传控件
    function initFileinput(ctrlId,imgPath) {
    	// console.log('initFileinput:'+ctrlId);
    	if(!imgPath){
    		imgPath='../dist/layuiadmin/img/defualt.png'
    	}
    	var control = $('#' + ctrlId);
    	control.fileinput({
    		uploadUrl : 'common/upload',
    		previewFileType : "image",
    		browseClass : "btn btn-success",
    		browseLabel : "选取图片",
    		browseIcon : "<i class=\"glyphicon glyphicon-picture\"></i> ",
    		showClose : false,
    		showRemove : false,
    		showCancel : false,
    		showUpload : false, // 是否显示上传按钮
    		showCaption : false,// 是否显示标题
    		showUploadedThumbs : false,
    		dropZoneEnabled: false,
    		allowedFileTypes : [ "image", "pdf" ],
    		removeFromPreviewOnError : true,// 当选择的文件不符合规则时，选择的文件不会出现在预览框中，只会显示错误信息
    		layoutTemplates : {
    			progress : '',
    			actionDelete : '',
    			actionUpload : '',
    		},
    		
    		/*previewSettings: { image: {'max-width': "150px", 'max-height':
    		"150px"}, pdf:{'max-width': "150px", 'max-height': "150px"}, },
    		*/
    		minFileCount : 1,
    		maxFileCount : 1,
    		autoReplace : true,// 当上传文件达到maxFileCount限制并且一些新的文件被选择后，是否自动替换预览中的文件。如果maxFileCount值有效，那么这个参数会起作用。
    		resizeImage : true,
    		maxImageWidth : 600,
    		maxImageHeight : 600,
    		initialPreview: [ //预览图片的设置
                "<img src='"+imgPath+"' class='file-preview-image' style='width:auto;height:auto;max-width:80%;max-height:80%;'>",
              ],
    	}).on('filebatchselected', function(event, files) {
    		control.fileinput('upload');
    	}).on('filebrowse', function(event) {
    		// console.log("File browse triggered.");
    		control.fileinput('clear');
    	}).on('fileerror', function(event, data, msg) {
    		$.modal.alertError("fileerror: " + msg);
    	}).on('fileimageresizeerror', function(event, data, msg) {
    		$.modal.alertError("fileimageresizeerror: " + msg);
    	}).on('fileuploaderror', function(event, data, msg) {
    		$.modal.alertError("fileuploaderror: " + msg);
    	}).on('filebatchuploaderror', function(event, data, msg) {
    		$.modal.alertError("filebatchuploaderror: " + msg);
    	});
    }