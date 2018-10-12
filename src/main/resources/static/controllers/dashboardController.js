define(["views/dashboard"], function(dashboard) {

    return {


        refreshDashboardTable: function() {


            obj = this;
            webix.ajax().get("/api/dashboard/hosts/", function(text, data, XmlHttpRequest) {


                $$("datatable_dashboard").showProgress({
                    type: "icon"
                });
                $$("datatable_dashboard").clearAll();
                oData = data.json();
                clusterName = "";
                for (var i = 0; i < oData.length; i++) {
                    if (clusterName != oData[i].clusterName) {
                        $$("datatable_dashboard").add({
                            hostName: "",
                            groupName: oData[i].clusterName

                        });
                        clusterName = oData[i].clusterName;
                    }

                    if (oData[i].label == null)
                        labelText = "";
                    else
                        labelText = "<span class='label_color' style='background-color: " + oData[i].label.color + ";'>" + oData[i].label.name + "</span>";

                    $$("datatable_dashboard").add({
                        hostName: oData[i].hostName,
                        groupName: oData[i].groupName,
                        os: oData[i].os,
                        hardware: oData[i].hardware,
                        software: oData[i].software,
                        gui: oData[i].gui,
                        label: labelText,
                        description: oData[i].description,
                        lastUpdate: oData[i].lastUpdate,
                        updateStatus: oData[i].updateStatus

                    });
                }
                $$("datatable_dashboard").hideProgress();
                $$("datatable_dashboard").refresh();
            });

        },

        init: function() {}
    }
});