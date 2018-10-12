define(["controllers/dashboardController"],
    function(dashboardController) {

        var ui = {

            view: "datatable",
            id: "datatable_dashboard",
            css: "datatable",
            autoConfig: true,
            autoWidth: true,
            rowHeight: 30,
            scheme: {
                $change: function(item) {
                    if (item.hostName == "")
                        item.$css = "webix_cell_group";
                }
            },
            columns: [{
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
                    header: "Software",
                    minWidth: webix.html.getTextSize("Software", "webix_table_cell webix_cell").width + 10,
                    fillspace: true
                },
                {
                    id: "label",
                    header: "Label",
                    adjust: "true"
                },
                {
                    id: "description",
                    header: "Description",
                    adjust: "true"
                },
                {
                    id: "gui",
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


            ]
        };

        return {
            $ui: ui,
            $oninit: function(view) {
                webix.extend($$("datatable_dashboard"), webix.ProgressBar);
                dashboardController.refreshDashboardTable();

            }
        };

    });