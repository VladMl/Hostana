define(["views/labels", "views/forms/label"], function(label, labelform) {

    return {

        init: function() {

            var onItemClick = $$("tbl_labels").attachEvent("onItemClick", function(id, e, node) {

                var item = this.getItem(id);
                if (id.column == "delete") {
                    webix.confirm({
                        text: "The label will be deleted. <br/> Are you sure?",
                        ok: "Yes",
                        cancel: "Cancel",
                        callback: function(res) {
                            if (res) {
                                webix.ajax().del("api/label/" + item.id, {}, function(text, xml, xhr) {
                                    webix.$$("tbl_labels").remove(item.id);
                                });
                            }
                        }
                    });
                }
                if (id.column == "edit") {

                    var win = this.$scope.ui(labelform.$ui);
                    $$("label-form").bind("tbl_labels");
                    $$("tbl_labels").setCursor(id);
                    win.recordId = id;
                    win.show();
                }
            })
        }
    }
});