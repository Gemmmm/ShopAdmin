//配置项
var colNumber = 1; // 列数
var rowNumber = 1; // 行数
var width = 300; // 框高
var height = 100; // 框宽
/**
 * 根据配置项搭建UI
 */
function makeUI() {
	// 从html标签读取配置项
	var htmlColNumber = jQuery('#licence').attr('LXXCol');
	var htmlRowNumber = jQuery('#licence').attr('LXXRow');
	var imgURL = jQuery('#licence').attr('LXXVal');
	colNumber = htmlColNumber ? parseInt(htmlColNumber) : colNumber;
	rowNumber = htmlRowNumber ? parseInt(htmlRowNumber) : rowNumber;

	var htmlWidth = jQuery('#licence').attr('LXXWidth');
	var htmlHeight = jQuery('#licence').attr('LXXHeight');
	width = width ? parseInt(htmlWidth) : width;
	height = height ? parseInt(htmlHeight) : height;

	// 开始搭建UI
	var html = "<table class='licence'>";

	for (var i = 0; i < rowNumber; i++) {
		html += "<tr>";
		for (var j = 0; j < colNumber; j++) {
			// html += "<td><input type='file' name='pic"+ (i * rowNumber + j +
			// 1) +"' /></td>";
			html += "<td><input type='file' id='file' class='file'  name='file' /></td>";
			// html += "<td><input type='file' id='file' class='file'
			// name='file' /></td>";
		}
		html += "</tr>";
	}
	html += "</table>";

	jQuery('#licence').html(html);
	jQuery('.licence tr td').css('width', width).css('height', height);
	if (imgURL != null && imgURL != "") {
		jQuery('.licence tr td').css('background',
				'url(' + imgURL + ') no-repeat center center').css(
				'background-size', '100%');
	}
}

/**
 * 根据input[file]对象获取添加的图片url
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
 * 选中图片将图片显示出来
 */
function changeToShow() {
	jQuery('input[name="file"]').change(
			function() {
				// 根据input[file]对象获取添加的图片url
				var src = getObjectURL(this.files[0]);
				// alert(this.files[0].size);
				if (this.files[0].size > 500 * 1024) {
					alert("图片大小不能超过500KB!");
					this.value = "";
					return;
				}

				jQuery(this).parent().css('background',
						'url(' + src + ') no-repeat center center').css(
						'background-size', '100%');
				parseLicence();
			});
}

/**
 * 根据配置项搭建UI
 */
function makeUICardFont() {
	// 从html标签读取配置项
	var htmlColNumber = jQuery('#cardFont').attr('LXXCol');
	var htmlRowNumber = jQuery('#cardFont').attr('LXXRow');
	var imgURL = jQuery('#cardFont').attr('LXXVal');
	colNumber = htmlColNumber ? parseInt(htmlColNumber) : colNumber;
	rowNumber = htmlRowNumber ? parseInt(htmlRowNumber) : rowNumber;

	var htmlWidth = jQuery('#cardFont').attr('LXXWidth');
	var htmlHeight = jQuery('#cardFont').attr('LXXHeight');
	width = width ? parseInt(htmlWidth) : width;
	height = height ? parseInt(htmlHeight) : height;

	// 开始搭建UI
	var html = "<table class='cardFont'>";

	for (var i = 0; i < rowNumber; i++) {
		html += "<tr>";
		for (var j = 0; j < colNumber; j++) {
			html += "<td><input type='file' id='cardFont' class='file' name='cardFont'/></td>";
			// html += "<td><input type='file' name='pic"+ (i * rowNumber + j +
			// 1) +"' /></td>";
			// html += "<td><input type='file' name='card' /></td>";
		}
		html += "</tr>";
	}
	html += "</table>";

	jQuery('#cardFont').html(html);
	jQuery('.cardFont tr td').css('width', width).css('height', height);
	if (imgURL != null && imgURL != "") {
		jQuery('.cardFont tr td').css('background',
				'url(' + imgURL + ') no-repeat center center').css(
				'background-size', '100%');
	}
}

/**
 * 选中图片将图片显示出来
 */
function changeToShowCardFont() {
	jQuery('input[name="cardFont"]').change(
			function() {
				// 根据input[file]对象获取添加的图片url
				var src = getObjectURL(this.files[0]);
				if (this.files[0].size > 500 * 1024) {
					alert("图片大小不能超过500KB!");
					this.value = "";
					return;
				}
				jQuery(this).parent().css('background',
						'url(' + src + ') no-repeat center center').css(
						'background-size', '100%');
				parseCardFont();
			});
}

/**
 * 根据配置项搭建UI
 */
function makeUICardBack() {
	// 从html标签读取配置项
	var htmlColNumber = jQuery('#cardBack').attr('LXXCol');
	var htmlRowNumber = jQuery('#cardBack').attr('LXXRow');
	var imgURL = jQuery('#cardBack').attr('LXXVal');
	colNumber = htmlColNumber ? parseInt(htmlColNumber) : colNumber;
	rowNumber = htmlRowNumber ? parseInt(htmlRowNumber) : rowNumber;

	var htmlWidth = jQuery('#cardBack').attr('LXXWidth');
	var htmlHeight = jQuery('#cardBack').attr('LXXHeight');
	width = width ? parseInt(htmlWidth) : width;
	height = height ? parseInt(htmlHeight) : height;

	// 开始搭建UI
	var html = "<table class='cardBack'>";

	for (var i = 0; i < rowNumber; i++) {
		html += "<tr>";
		for (var j = 0; j < colNumber; j++) {
			html += "<td><input type='file' class='file' id='cardBack' name='cardBack' /></td>";
			// html += "<td><input type='file' name='pic"+ (i * rowNumber + j +
			// 1) +"' /></td>";
			// html += "<td><input type='file' name='card' /></td>";
		}
		html += "</tr>";
	}
	html += "</table>";

	jQuery('#cardBack').html(html);
	jQuery('.cardBack tr td').css('width', width).css('height', height);
	if (imgURL != null && imgURL != "") {
		jQuery('.cardBack tr td').css('background',
				'url(' + imgURL + ') no-repeat center center').css(
				'background-size', '100%');
	}
}

/**
 * 选中图片将图片显示出来
 */
function changeToShowCardBack() {
	jQuery('input[name="cardBack"]').change(
			function() {
				// 根据input[file]对象获取添加的图片url
				var src = getObjectURL(this.files[0]);
				if (this.files[0].size > 500 * 1024) {
					alert("图片大小不能超过500KB!");
					this.value = "";
					return;
				}
				jQuery(this).parent().css('background',
						'url(' + src + ') no-repeat center center').css(
						'background-size', '100%');
				parseCardBack();
			});
}

/**
 * 根据配置项搭建UI
 */
function makeUILogo() {
	// 从html标签读取配置项
	var htmlColNumber = jQuery('#companyLogo').attr('LXXCol');
	var htmlRowNumber = jQuery('#companyLogo').attr('LXXRow');
	var imgURL = jQuery('#companyLogo').attr('LXXVal');
	colNumber = htmlColNumber ? parseInt(htmlColNumber) : colNumber;
	rowNumber = htmlRowNumber ? parseInt(htmlRowNumber) : rowNumber;

	var htmlWidth = jQuery('#companyLogo').attr('LXXWidth');
	var htmlHeight = jQuery('#companyLogo').attr('LXXHeight');
	width = width ? parseInt(htmlWidth) : width;
	height = height ? parseInt(htmlHeight) : height;

	// 开始搭建UI
	var html = "<table class='companyLogo'>";

	for (var i = 0; i < rowNumber; i++) {
		html += "<tr>";
		for (var j = 0; j < colNumber; j++) {
			html += "<td><input type='file' class='file'  name='companyLogoImg' /></td>";
		}
		html += "</tr>";
	}
	html += "</table>";

	jQuery('#companyLogo').html(html);
	jQuery('.companyLogo tr td').css('width', width).css('height', height);
	if (imgURL != null && imgURL != "") {
		jQuery('.companyLogo tr td').css('background',
				'url(' + imgURL + ') no-repeat center center').css(
				'background-size', '100%');
	}
}

/**
 * 选中图片将图片显示出来
 */
function changeToShowLogo() {
	jQuery('input[name="companyLogoImg"]').change(
			function() {
				// 根据input[file]对象获取添加的图片url
				var src = getObjectURL(this.files[0]);
				if (this.files[0].size > 500 * 1024) {
					alert("图片大小不能超过500KB!");
					this.value = "";
					return;
				}
				jQuery(this).parent().css('background',
						'url(' + src + ') no-repeat center center').css(
						'background-size', '100%');
				parseCardBack();
			});
}

jQuery(document).ready(function() {

	makeUILogo();
	changeToShowLogo();

	makeUI();
	changeToShow();

	makeUICardFont();
	changeToShowCardFont();

	makeUICardBack();
	changeToShowCardBack();

});