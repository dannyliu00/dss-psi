(function() {
    var routing = sellInNamespace('sellIn.routing');

    function routerConfig($routeProvider,
                          defaultPageUrl, defaultPageControllerName, defaultPageTemplateUrl,
                          dealerSummaryPageUrl, dealerSummaryPageControllerName, dealerSummaryPageTemplateUrl,
                          profilePageUrl, profilePageControllerName, profilePageTemplateUrl,
                          defaultRedirection) {

        $routeProvider
            .when(defaultPageUrl, {
                templateUrl: defaultPageTemplateUrl,
                controller: defaultPageControllerName
            })
            .when(dealerSummaryPageUrl, {
                templateUrl: dealerSummaryPageTemplateUrl,
                controller: dealerSummaryPageControllerName
            })
            .when(profilePageUrl, {
                templateUrl: profilePageTemplateUrl,
                controller: profilePageControllerName
            })
            .otherwise({
                redirectTo: defaultRedirection
            });
    }

    routing.routerConfig = routerConfig;
}());