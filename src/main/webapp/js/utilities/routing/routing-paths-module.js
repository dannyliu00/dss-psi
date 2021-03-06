(function () {
    angular.module('sellIn.routing.paths', [])
        .constant('defaultPageUrl', '/default')
        .constant('defaultPageControllerName', 'defaultPageController')
        .constant('defaultPageTemplateUrl', 'js/pages/dealer-summary/profile-summary.html')

        .constant('dealerSummaryPageUrl', '/dealerSummary/:type/:status')
        .constant('dealerSummaryPageControllerName', 'dealerProfileSummaryCtrl')
        .constant('dealerSummaryPageTemplateUrl', 'js/pages/dealer-summary/profile-summary.html')

        .constant('profilePageUrl', '/dealerProfile/profile/:profileId/type/:type')
        .constant('profilePageControllerName', 'profileController')
        .constant('profilePageTemplateUrl', 'js/pages/profile-detail/profile-detail-template.html')

        .constant('dsmUrl', '/nonDealerSummary/:type/:status')
        .constant('dsmControllerName', 'nonDealerSummaryController')
        .constant('dsmTemplateUrl', 'js/pages/non-dealer-summary/non-dealer-summary-page-template.html')

        .constant('defaultRedirection', '/default');
}());
