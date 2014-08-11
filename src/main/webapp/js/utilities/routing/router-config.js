(function() {
    var routing = sellInNamespace('sellIn.routing');

    function routerConfig($routeProvider,
                          defaultPageUrl, defaultPageControllerName, defaultPageTemplateUrl,
                          dealerSummaryPageUrl, dealerSummaryPageControllerName, dealerSummaryPageTemplateUrl,
                          profilePageUrl, profilePageControllerName, profilePageTemplateUrl,
                          defaultRedirection, dsmUrl, dsmTemplateUrl, dsmControllerName) {

        $routeProvider
            .when(defaultPageUrl, {
                templateUrl: defaultPageTemplateUrl,
                controller: defaultPageControllerName,
                resolve:{
                    'translationData':function(translationPSI){
                      return translationPSI.promise;
                    }
            	}
            })
            .when(dealerSummaryPageUrl, {
                templateUrl: dealerSummaryPageTemplateUrl,
                controller: dealerSummaryPageControllerName,
                resolve:defaultPageControllerName.resolve
            })
            .when(profilePageUrl, {
                templateUrl: profilePageTemplateUrl,
                controller: profilePageControllerName
            })
            .when(dsmUrl, {
                templateUrl: dsmTemplateUrl,
                controller: dsmControllerName
            })
            .otherwise({
                redirectTo: defaultRedirection
            });
    }

    routing.routerConfig = routerConfig;
}());