/**
 * Created by bericks on 5/12/2014.
 */

angular.module('sellIn', [
        'ngRoute',
        'polaris.directives.commonHeader',
        'polaris.directives.commonSearch',
        'polaris.directives.commonFooter',
        'polaris.directives.tabs',
        'polaris.directives.pagination',
        'sellIn.directives.dealerInfo',
        'sellIn.resources.dealerProfiles',
        'sellIn.resources.dealer',
        'sellIn.pages.dealerProfileSummary',
        'sellIn.pages.profile'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/dealerSummary', {
                templateUrl: 'js/pages/dealer-summary/profile-summary.html',
                controller: 'dealerProfileSummaryCtrl'
            })
            .when('/dealerProfile/:id', {
                templateUrl: 'js/pages/profile-detail/profile-detail-template.html',
                controller: 'profileController'
            })
            .otherwise({
                redirectTo: '/dealerSummary'
            });
    });
