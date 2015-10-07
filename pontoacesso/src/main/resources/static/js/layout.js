$(document).ready(function() {
	//get height container sidebar and remove height of header sidebar
	var heightContainerScroll = ($(".container-sidebar").outerHeight()) - 60;
	//set height to container scroll
	$("#navbar-collapse-01").css({
		'max-height' : heightContainerScroll,
		'height' : heightContainerScroll				
	});
	//Build scrollbar
	$("#navbar-collapse-01").mCustomScrollbar();
});
$(window).resize(function() {
	//get height container sidebar and remove height of header sidebar
	var heightContainerScroll = ($(".container-sidebar").outerHeight()) - 60;
	//set height to container scroll
	$("#navbar-collapse-01").css({
		'max-height' : heightContainerScroll,
		'height' : heightContainerScroll				
	});
	//Destroy/Rebuild scrollbar
	$("#navbar-collapse-01").mCustomScrollbar("destroy");
	$("#navbar-collapse-01").mCustomScrollbar();
	
});