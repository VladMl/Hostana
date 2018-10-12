define([], function() {


    var menu_data = [{
            "icon": "home",
            "id": "dashboard",
            "value": "Dashboard"
        },
        {
            "icon": "cog",
            "id": "configuration",
            "value": "Configuration",
            data: [{
                    id: "hosts",
                    value: "Hosts",
                    icon: "server",
                },
                {
                    id: "labels",
                    value: "Labels",
                    icon: "tags",
                }
            ]
        }
    ];


    var menu = {
        view: "sidebar",
        id: "app:menu",
        scroll: true,
        data: menu_data,
        on: {

            onAfterSelect: function(id) {
                var item = this.getItem(id);
                if (id.substring(0, 6) == 'topic_') {
                    this.$scope.show("./topic");
                }

                this.$scope.show("./" + id);

                webix.$$("title").parse({
                    title: item.value
                });

            },
            onAfterLoad: function() {
                var maxWidth = 0;
                this.data.each(function(item) {
                    var size = webix.html.getTextSize(item.value);
                    var width = 170 + size.width;
                    if (width > maxWidth)
                        maxWidth = width;
                });
                this.define("width", maxWidth);
                this.resize();

            }
        }
    }


    var body = {
        view: "layout",
        rows: [{
                height: 49,
                id: "title",
                css: "title",
                template: "<div class='header'>#title#</div>",
                data: {
                    text: "",
                    title: ""
                }
            },
            {
                view: "layout",
                type: "space",
                rows: [{
                    $subview: true
                }]
            }
        ]
    };


    var layout = {
        rows: [{
                view: "toolbar",
                padding: 3,
                elements: [{
                        view: "button",
                        type: "icon",
                        icon: "bars",
                        width: 37,
                        align: "left",
                        css: "app_button",
                        click: function() {

                            $$("app:menu").toggle()
                            $$("app:menu").define("scroll", "false");
                            $$("app:menu").refresh();


                        }
                    },
                    {
                        view: "label",
                        label: "Hostana"
                    },
                    {}
                ]
            },
            {
                cols: [
                    menu,
                    body
                ]
            }
        ]
    }


    return {
        $ui: layout,
        $menu: "app:menu",
        $oninit: function(view, scope) {

            webix.UIManager.addHotKey("esc", function(view) {
                if (view) {
                    var top = view.getTopParentView();
                    if (top && top.setPosition)
                        top.hide();
                }
            });

            $$("app:menu").toggle()
            $$("app:menu").define("scroll", "false");
            $$("app:menu").refresh();
            var popup = $$("app:menu").getPopup().queryView({
                view: "list"
            });
            popup.define({
                height: 200,
                scroll: true,
                autoheight: false
            });
            popup.resize();

        }
    };

});