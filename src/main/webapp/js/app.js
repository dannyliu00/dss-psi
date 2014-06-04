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
        'sellIn.directives.unsavedChanges',
        'sellIn.directives.profiles',
        'sellIn.directives.dealerInfo',
        'sellIn.resources.dealerProfiles',
        'sellIn.resources.dealerProfile',
        'sellIn.resources.dealer',
        'sellIn.pages.dealerProfileSummary',
        'sellIn.pages.profile'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/dealerSummary/:dealerId', {
                templateUrl: 'js/pages/dealer-summary/profile-summary.html',
                controller: 'dealerProfileSummaryCtrl'
            })
            .when('/dealerProfile/:dealerId/profile/:profileId/type/:typeId', {
                templateUrl: 'js/pages/profile-detail/profile-detail-template.html',
                controller: 'profileController'
            })
            .otherwise({
                redirectTo: '/dealerSummary/2021900'
            });
    });
