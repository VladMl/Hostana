define(function() {

    return {
        $ui: {
            view: "window",
            modal: true,
            id: "window-label",
            position: "center",
            head: "Add/Edit label",
            recordId: 0,
            body: {
                paddingY: 20,
                paddingX: 30,
                view: "form",
                elementsConfig: {
                    labelWidth: 120
                },
                id: "form-label",
                elements: [{
                        view: "text",
                        name: "name",
                        label: "Name",
                        id: "name",
                        width: 350
                    }, {
                        view: "colorpicker",
                        label: "Color",
                        name: "color",
                        id: "color",
                        value: "#000000"
                    }, {
                        margin: 10,
                        cols: [{}, {
                            view: "button",
                            label: "Ok",
                            type: "form",
                            align: "center",
                            width: 120,
                            click: function() {
                                if ($$("window-label").recordId > 0)
                                    webix.ajax().put("/api/label/" + $$("window-label").recordId, $$('form-label').getValues()).then(function(data) {
                                        $$("tbl_labels").load($$("tbl_labels").config.url);
                                        $$("window-label").close();
                                    }).fail(function(err) {
                                        alert(err);
                                    });
                                else
                                    webix.ajax().post("/api/label", $$('form-label').getValues()).then(function(data) {
                                        $$("tbl_labels").load($$("tbl_labels").config.url);
                                        $$("window-label").close();
                                    }).fail(function(err) {
                                        alert(err);
                                    });
                            }
                        }, {
                            view: "button",
                            label: "Cancel",
                            align: "center",
                            width: 120,
                            click: function() {
                                webix.$$("window-label").close();
                            }
                        }]
                    }

                ]
            },
            on: {
                onShow: function() {
                    webix.UIManager.setFocus($$('name'));
                }
            }
        }
    };

});