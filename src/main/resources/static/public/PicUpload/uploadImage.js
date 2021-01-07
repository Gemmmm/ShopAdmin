
/**
根据配置项搭建UI
*/
function makeUI(){
	//配置项
	var colNumber = 1;	//列数
	var rowNumber = 2;	//行数
	var width = 300;	//框高
	var height = 100;	//框宽
//从html标签读取配置项
	var htmlColNumber = jQuery('#LXXUploadPic').attr('LXXCol');
	var htmlRowNumber = jQuery('#LXXUploadPic').attr('LXXRow');
	var imgURL = jQuery('#LXXUploadPic').attr('LXXVal');
	colNumber = htmlColNumber ? parseInt(htmlColNumber) : colNumber;
	rowNumber = htmlRowNumber ? parseInt(htmlRowNumber) : rowNumber;

	var htmlWidth = jQuery('#LXXUploadPic').attr('LXXWidth');
	var htmlHeight = jQuery('#LXXUploadPic').attr('LXXHeight');
	width = width ? parseInt(htmlWidth) : width;
	height = height ? parseInt(htmlHeight) : height;


	//开始搭建UI
	var html = "<table class='LXXUploadPic'>";
	
	for(var i = 0; i<rowNumber; i++){
		html += "<tr>";
		for(var j = 0; j<colNumber; j++){
			html += "<td><input type='file' id='file' class='file'  name='file' /></td>";
		}
		html += "</tr>";
	}
	
	html += "</table>";

	jQuery('#LXXUploadPic').html(html);
	jQuery('.LXXUploadPic tr td').css('width', width).css('height', height);
	if(imgURL!=null&&imgURL!=""){
		jQuery('.LXXUploadPic tr td').css('background', 'url(' + imgURL + ') no-repeat center center').css('background-size', '100%');
	}
}

/**
根据input[file]对象获取添加的图片url
*/
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
        url = window.createObjectURL(file)
    } else if (window.URL != undefined) {
        url = window.URL.createObjectURL(file)
    } else if (window.webkitURL != undefined) {
        url = window.webkitURL.createObjectURL(file)
    }
    return url
};

/**
选中图片将图片显示出来
*/
function changeToShow(){
  	jQuery('input[name="file"]').change(function(e){
  		console.log(this.files[0]);
  		if(this.files[0].type=="image/png"||this.files[0].type=="image/jpeg"){
			if (this.files[0].size > 500 * 1024) {
				alert("图片大小不能超过500KB!");
				this.value = "";
				return;
			}
			 var src = getObjectURL(this.files[0]);
	  		jQuery(this).parent().css('background', 'url(' + src + ') no-repeat center center').css('background-size', '100%');
		}else if(this.files[0].type=="video/mp4"){
			if (this.files[0].size > 10000 * 1024) {
				alert("视频大小不能超过10MB!");
				this.value = "";
				return;
			} 
			 var src = getObjectURL(this.files[0]);
			 jQuery(this).parent()
			 
		}else{
			alert("格式不正确，图片是png或jpg格式，视频是mp4格式");
			this.value="";
  			return;
		}
		
  		
  	});
}

jQuery(document).ready(function(){
	//根据配置项搭建UI
	makeUI();
	//选中图片将图片显示出来
	changeToShow();
});