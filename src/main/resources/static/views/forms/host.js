define(function() {

    return {
        $ui: {
            view: "window",
            modal: true,
            id: "window-host",
            position: "center",
            head: "Edit host",
            body: {
                paddingY: 20,
                paddingX: 30,
                view: "form",
                elementsConfig: {
                    labelWidth: 120
                },
                id: "form-host",
                elements: [{
                        view: "text",
                        name: "hostName",
                        label: "Hostname",
                        id: "hostName",
                        width: 650,
                        disabled: true
                    },
                    {
                        view: "text",
                        name: "groupName",
                        label: "Host group",
                        id: "groupName",
                        disabled: true
                    },
                    {

                        view: "richselect",
                        name: "label",
                        label: "Label",
                        id: "label",
                        value: 1,
                        options: {
                            body: {
                                template: "#name#",
                                url: "api/labels",
                            }
                        }
                    },
                    {
                        view: "text",
                        name: "hardware",
                        label: "Hardware",
                        id: "hardware"
                    },
                    {
                        view: "text",
                        name: "os",
                        label: "OS",
                        id: "os"
                    },
                    {
                        view: "textarea",
                        name: "software",
                        label: "Software",
                        id: "software",
                        height: 60
                    },
                    {
                        view: "textarea",
                        name: "gui",
                        label: "GUI",
                        id: "gui",
                        height: 60
                    },
                    {
                        view: "textarea",
                        name: "description",
                        label: "Description",
                        id: "description"
                    },
                    {
                        margin: 10,
                        cols: [{},
                            {
                                view: "button",
                                label: "Ok",
                                type: "form",
                                align: "center",
                                width: 120,
                                click: function() {
                                    webix.ajax().put("/api/host/" + $$("window-host").recordId, $$('form-host').getValues()).then(function(data) {
                                        $$("datatable_hosts").load($$("datatable_hosts").config.url);
                                        $$("window-host").close();
                                    }).fail(function(err) {
                                        alert(err);
                                    });


                                }
                            },
                            {
                                view: "button",
                                label: "Cancel",
                                align: "center",
                                width: 120,
                                click: function() {
                                    webix.$$("window-host").close();
                                }
                            }
                        ]
                    }

                ]
            }
        }
    };

});