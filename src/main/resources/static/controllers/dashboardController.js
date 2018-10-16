define(["views/dashboard", "views/forms/host"], function(dashboard, hostform) {

    return {

        refreshDashboardTable: function() {
           $$("datatable_dashboard").showProgress({
               type: "icon"
           });

            obj = this;
            webix.ajax().get("/api/dashboard/hosts/", function(text, data, XmlHttpRequest) {


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


                    if (oData[i].label == null) {
                        labelText = "";
                        labelId = null;
                    } else {
                        labelText = "<span class='label_color' style='background-color: " + oData[i].label.color + ";'>" + oData[i].label.name + "</span>";
                        labelId = oData[i].label.id;
                    }


                    $$("datatable_dashboard").add({
                        id: oData[i].id,
                        hostName: oData[i].hostName,
                        groupName: oData[i].groupName,
                        os: oData[i].os,
                        hardware: oData[i].hardware,
                        software: oData[i].software,
                        softwareView: oData[i].softwareView,
                        gui: oData[i].gui,
                        guiView: oData[i].guiView,
                        label: labelId,
                        labelView: labelText,
                        description: oData[i].description,
                        lastUpdate: oData[i].lastUpdate,
                        updateStatus: oData[i].updateStatus

                    });
                }
                $$("datatable_dashboard").hideProgress();

                $$("datatable_dashboard").refresh();





            });

        },

        init: function() {

                Window.refreshDashboardTable = this.refreshDashboardTable;

                var onClick = $$("datatable_dashboard").attachEvent("onItemClick", function(id, e, node) {

                var item = this.getItem(id);

                    var win = this.$scope.ui(hostform.$ui);
                    $$("form-host").bind("datatable_dashboard");
                    $$("datatable_dashboard").setCursor(id);
                    win.recordId = id;
                    win.show();


                });

            var onMouseMove = $$("datatable_dashboard").attachEvent("onMouseMoving", function(ev) {
            var id = this.locate(ev);

            if (id != null)
              if (id.column == "hostName") {
               if (id != this.last_used_id)
                  this.removeCellCss(this.last_used_id, "hostName", "hover");
               this.addCellCss(id, "hostName", "hover");
               this.last_used_id = id;
              } else
               this.removeCellCss(this.last_used_id, "hostName", "hover");



        });
        }
    }
});