/*
	App configuration
*/

define([
	"libs/webix-mvc-core/core"
], function(
	core
){

	//configuration
	var app = core.create({
		id:	        "Hostana",
		name:		"Hostana",
		version:	"0.1.0",
		debug:		true,
		start:		"/app/dashboard"
	});




	return app;
});
