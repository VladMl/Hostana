define(["views/forms/host", "controllers/hostsController"],
    function(hostform, hostsController) {




        var ui = {
            id: "datatable_hosts",
            view: "datatable",
            rowHeight: 30,
            columns: [{
                    id: "hostName",
                    header: "Hostname",
                    adjust: "true",
                },
                {
                    id: "groupName",
                    header: "Group",
                    adjust: "true"
                },
                {
                    id: "hardware",
                    header: "Hardware",
                    adjust: "true"
                },
                {
                    id: "os",
                    header: "OS",
                    adjust: "true"
                },
                {
                    id: "software",
                    header: "Software",
                    fillspace: true
                },
                {
                    id: "label",
                    header: "Label",
                    adjust: "true",
                    template: function(obj, common, value, config) {
                        if (obj.label == null)
                            return "";
                        else
                            return "<span class='label_color' style='background-color: " + obj.label.color + ";'>" + obj.label.name + "</span>";
                    }
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
                    id: "edit",
                    header: "&nbsp;",
                    width: 50,
                    template: "<span  style=' cursor:pointer;' class='webix_icon fa-pencil'></span>"
                }
            ],
            url: "/api/hosts/"
        };



        return {
            $ui: ui,
            $oninit: function(view) {
                hostsController.init();
            }
        };

    });