define(["controllers/dashboardController"],
    function(dashboardController) {

        var ui = {

            view: "datatable",
            id: "datatable_dashboard",
            css: "datatable",
            rowHeight: 30,
            scheme: {
                $change: function(item) {
                    if (item.hostName == "")
                        item.$css = "webix_cell_group";
                }
            },
            columns: [
                {   id: "id",
                    hidden: "true"
                },
                {
                    id: "hostName",
                    header: "Hostname",
                    adjust: "data"
                },
                {
                    id: "groupName",
                    header: "Group",
                    adjust: "data"
                },
                {
                    id: "hardware",
                    header: "Hardware",
                    adjust: "true"
                },
                {
                    id: "os",
                    header: "OS",
                    adjust: "data"
                },
                {
                    id: "software",
                    hidden: "true"
                },
                {
                    id: "softwareView",
                    header: "Software",
                    fillspace: true,
                    minWidth: webix.html.getTextSize("Software", "webix_table_cell webix_cell").width + 10
                },
                {   id: "label",
                    hidden: "true"
                },
                {
                    id: "labelView",
                    header: "Label",
                    adjust: "true",
                    editor: "combo"
                },
                {
                    id: "description",
                    header: "Description",
                    adjust: "true",
                    editor: "popup"
                },
                {   id: "gui",
                    hidden: "true"
                },
                {
                    id: "guiView",
                    header: "GUI",
                    adjust: "true"
                },
                {
                    id: "lastUpdate",
                    header: "Last update",
                    minWidth: 134,
                    adjust: "true"
                },
                {
                    id: "updateStatus",
                    header: "Status",
                    adjust: "header",
                    css: {
                        'text-align': 'center'
                    }
                }


            ],
            onMouseMove:{}


        };

        return {
            $ui: ui,
            $oninit: function(view) {
                webix.extend($$("datatable_dashboard"), webix.ProgressBar);
                dashboardController.init();
                dashboardController.refreshDashboardTable();

            }
        };

    });