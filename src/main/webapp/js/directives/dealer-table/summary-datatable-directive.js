(function () {
    var summaryDatatables = sellInNamespace('sellIn.directives.summarydatatables');

    function SummaryDatatablesDirective($http, $compile, $templateCache) {

        return {
            restrict: 'E',
            controller: summaryDatatables.SummaryDatatablesController,
            link: function (scope, element, attrs) {
                scope.$watch(attrs.profiles, function (profiles) {
                    if (profiles != null) {
                        loadTemplate('js/directives/dealer-table/summary-datatable-template.html');
                    }
                });

                function loadTemplate(template) {
                    $http.get(template, { cache: $templateCache })
                        .success(function (templateContent) {
                            element.replaceWith($compile(templateContent)(scope));
                        });
                }
            }
        };
    }

    summaryDatatables.SummaryDatatablesDirective = SummaryDatatablesDirective;
})();