define(["views/hosts", "views/forms/host"], function(host, hostform) {

    return {

        init: function() {

            var onItemClick = $$("datatable_hosts").attachEvent("onItemClick", function(id, e, node) {

                var item = this.getItem(id);
                if (id.column == "edit") {

                    var win = this.$scope.ui(hostform.$ui);
                    $$("form-host").bind("datatable_hosts");
                    $$("datatable_hosts").setCursor(id);
                    win.recordId = id;
                    win.show();
                }
            })

        }
    }
});