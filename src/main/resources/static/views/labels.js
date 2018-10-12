define(["views/forms/label", "controllers/labelsController"],
	function (labelform, labelsController) {

	var controls = [{
			view: "button",
			type: "iconButton",
			icon: "plus",
			label: "Add",
			css: "button_primaty button_raised",
			width: 130,
			click: function () {
				this.$scope.ui(labelform.$ui).show();
			}
		}, {}

	];

	var grid = {
		margin: 10,
		rows: [{
				id: "tbl_labels",
				view: "datatable",
				rowHeight : 30,
				columns: [{
						id: "name",
						header: "Name",
						fillspace: true,
						template: "<span class='label_color' style='background-color: #color#;'>#name#</span>"
					}, {
						id: "color",
						header: "Color"
					}, {
						id: "edit",
						header: "&nbsp;",
						width: 50,
						template: "<span  style=' cursor:pointer;' class='webix_icon fa-pencil'></span>"
					}, {
						id: "delete",
						header: "&nbsp;",
						width: 50,
						template: "<span  style='color:#777777; cursor:pointer;' class='webix_icon fa-trash'></span>"
					}

				],
				"export": true,
				url: "api/labels"

			}
		]

	};

	var ui = {

		type: "space",
		rows: [{
				height: 40,
				cols: controls
			}, {
				rows: [
					grid
				]
			}
		]

	};

	return {
		$ui: ui,
		$oninit: function (view) {
			labelsController.init();
		}
	};

});
