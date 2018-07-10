if ($) {
	$.ajaxSetup({
		converters : {
			'* text' : window.String,
			'text html' : true,
			'text json' : jQuery.parseJSON,
			'text xml' : jQuery.parseXML,
			'text script' : window.eval
		},
		beforeSend : function(xhr, settings) {
			var jsonData = null;
			if (settings.type == 'GET') {
				jsonData = JSON.stringify(jQuery.deparam.querystring(settings.url));
			} else if (settings.contentType.indexOf('application/x-www-form-urlencoded') != -1) {
				jsonData = JSON.stringify(jQuery.deparam(settings.data));
			} else {
				jsonData = settings.data;
			}
			xhr.setRequestHeader('jsp-play-json-data',encodeURIComponent(jsonData));
		}
	});
}