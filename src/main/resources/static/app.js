/*
	App configuration
*/

define([
	"libs/webix-mvc-core/core",
	"libs/webix-mvc-core/plugins/menu"

], function(
	core, menu
){

	//configuration
	var app = core.create({
		id:	        "Hostana",
		name:		"Hostana",
		version:	"0.1.0",
		debug:		true,
		start:		"/app/dashboard"
	});

	app.use(menu);



	return app;
});
