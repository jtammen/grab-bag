/* Foundation v2.2.1 http://foundation.zurb.com */
jQuery(document).ready(function($) {
	/* ALERT BOXES ------------ */
	$(".alert-box").delegate("a.close", "click", function(event) {
		event.preventDefault();
		$(this).closest(".alert-box").fadeOut(function(event) {
			$(this).remove();
		});
	});

	/* PLACEHOLDER FOR FORMS ------------- */
	$('input, textarea').placeholder();

	/* Init date stuff */
	moment.lang(config.lang);
	$('.localtime.timeago').each(function() {
		var el = $(this);
		var mom = moment(Number(el.attr('data-tstamp')));
		el.attr('title', mom.format(config.dateFormat)).text(mom.calendar());
	});
	
	/* TOOLTIPS ------------ */
	$(this).tooltips();
});

/* Facebook SDK */
window.fbAsyncInit = function() {
	FB.init({
		appId: config.facebookAppId, // App ID
		//channelUrl: '//WWW.YOUR_DOMAIN.COM/channel.html', // Channel File
		status: true, // check login status
		cookie: true, // enable cookies to allow the server to access the session
		xfbml: true  // parse XFBML
	});
	// Additional initialization code here
};
(function(d) {
	var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	if (d.getElementById(id)) {return;}
	js = d.createElement('script'); js.id = id; js.async = true;
	js.src = "//connect.facebook.net/"+config.locale+"/all.js";
	ref.parentNode.insertBefore(js, ref);
}(document));

$(window).load(function() {
	$('#features-slider').orbit({
		animation : 'fade', // fade, horizontal-slide, vertical-slide,
							// horizontal-push
		animationSpeed : 800, // how fast animtions are
		timer : false, // true or false to have the timer
		advanceSpeed : 4000, // if timer is enabled, time between transitions
		pauseOnHover : false, // if you hover pauses the slider
		startClockOnMouseOut : false, // if clock should start on MouseOut
		startClockOnMouseOutAfter : 1000, // how long after MouseOut should
											// the timer start again
		directionalNav : true, // manual advancing directional navs
		captions : false, // do you want captions?
		captionAnimation : 'fade', // fade, slideOpen, none
		captionAnimationSpeed : 800, // if so how quickly should they animate
										// in
		bullets : true, // true or false to activate the bullet navigation
		bulletThumbs : false, // thumbnails for the bullets
		bulletThumbLocation : '', // location from this file where thumbs will
									// be
		afterSlideChange : function() {},
		fluid: true
	});
});
