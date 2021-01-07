jQuery(function() {
	var delParent;
	var defaults = {
		fileType : [ "jpg", "png", "bmp", "jpeg" ],
		fileSize : 1024 * 1024 * 10
	};
	jQuery("#picFile").change( function() {
		var idFile = jQuery(this).attr("id");
		var file = document.getElementById(idFile);
		var imgContainer = jQuery(this).parents(".z_photo");
		var fileList = file.files;
		console.log("file",this.files[0]);
		var input = jQuery(this).parent();
		var imgArr = [];
		var numUp = imgContainer.find(".up-section").length;
		var totalNum = numUp + fileList.length;
		if (fileList.length > 5 || totalNum > 5) {
			alert("上传图片数目不可以超过5个，请重新选择");
		} else if (numUp < 5) {
			fileList = validateUp(fileList);
			for (var i = 0; i < fileList.length; i++) {
				var imgUrl = window.URL.createObjectURL(fileList[i]);
				imgArr.push(imgUrl);
				var $section = $("<sectiojQuery class='up-section fl loading'>");
				imgContainer.prepend($section);
				var $span = $("<span class='up-span'>");
				$span.appendTo($section);
				var $img0 = $("<img class='close-upimg'>").on(
						"click", function(event) {
							event.preventDefault();
							event.stopPropagation();
							$(".works-mask").show();
							delParent = jQuery(this).parent();
						});
				$img0.attr("src", "../public/PicUploadMulti/image/a7.png")
						.appendTo($section);
				var $img = $("<img class='up-img up-opcity'>");
				$img.attr("src", imgArr[i]);
				$img.appendTo($section);
				var $p = $("<p class='img-name-p'>");
				$p.html(fileList[i].name).appendTo($section);
				var $input = $("<input id='pic' name='pics' value='"+this.files[i]+"' type='file' style='display:none'>");
				$input.appendTo($section);
			}
		}
		uploadPic();
		setTimeout(function() {
			jQuery(".up-section").removeClass("loading");
			jQuery(".up-img").removeClass("up-opcity");
		}, 450);
		
		numUp = imgContainer.find(".up-section").length;
		if (numUp >= 5) {
			jQuery(this).parent().hide();
		}
		jQuery(this).val("");
	});
	jQuery(".z_photo").delegate(".close-upimg", "click", function() {
		jQuery(".works-mask").show();
		delParent = jQuery(this).parent();
	});
	jQuery(".wsdel-ok").click(function() {
		jQuery(".works-mask").hide();
		var numUp = delParent.siblings().length;
		if (numUp < 6) {
			delParent.parent().find(".z_file").show();
		}
		delParent.remove();
	});
	jQuery(".wsdel-no").click(function() {
		jQuery(".works-mask").hide();
	});
	function validateUp(files) {
		var arrFiles = [];
		for (var i = 0, file; file = files[i]; i++) {
			var newStr = file.name.split("").reverse().join("");
			if (newStr.split(".")[0] != null) {
				var type = newStr.split(".")[0].split("").reverse().join("");
				if (jQuery.inArray(type, defaults.fileType) > -1) {
					if (file.size >= defaults.fileSize) {
						alert(file.size);
						alert('您这个"' + file.name + '"文件大小过大');
					} else {
						arrFiles.push(file);
					}
				} else {
					alert('您这个"' + file.name + '"上传类型不符合');
				}
			} else {
				alert('您这个"' + file.name + '"没有类型, 无法识别');
			}
		}
		return arrFiles;
	}
	function uploadPic() {
		console.log("11");
		$.ajaxFileUpload({
			url : './uploadPic', //用于文件上传的服务器端请求地址 
			secureuri : false, //是否需要安全协议，一般设置为false 
			fileElementId : [ 'picFile' ], //文件上传域的ID
			dataType : 'json', //返回值类型 一般设置为json 
			success : function(data, status) {
				console.log(data);
				
			},
			error : function(data, status, e) {
				console.log("error:" + e);
			}
		});
	}
	
})