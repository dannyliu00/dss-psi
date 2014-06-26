(function() {
    var dsmSummaryDatatables = sellInNamespace('sellIn.directives.dsmsummarydatatables');

    function DsmSummaryDatatablesDirective($http, $compile, $templateCache) {

        return {
            restrict: 'E',
            controller: dsmSummaryDatatables.DsmSummaryDatatablesController,
            templateUrl:'js/directives/dsm-table/dsm-summary-datatables-template.html'
//            link: function (scope, element, attrs) {
//                scope.$watch(attrs.profiles, function(profiles) {
//                    if(profiles != null) {
//                    	loadTemplate('js/directives/dsm-table/dsm-summary-datatables-template.html');
//                    }
//                });
                
//                function loadTemplate(template) {
//                    $http.get(template, { cache: $templateCache })
//                        .success(function(templateContent) {
//                            element.replaceWith($compile(templateContent)(scope));
//                        });
//                }
//            }
        };
    }

    dsmSummaryDatatables.DsmSummaryDatatablesDirective = DsmSummaryDatatablesDirective;
})();