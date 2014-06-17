(function() {
    angular.module('sellIn.routing.paths', [])
        .constant('defaultPageUrl', '/default')
        .constant('defaultPageControllerName', 'defaultPageController')
        .constant('defaultPageTemplateUrl', 'js/pages/dealer-summary/profile-summary.html')

        .constant('dealerSummaryPageUrl', '/dealerSummary/:dealerId')
        .constant('dealerSummaryPageControllerName', 'dealerProfileSummaryCtrl')
        .constant('dealerSummaryPageTemplateUrl', 'js/pages/dealer-summary/profile-summary.html')

        .constant('profilePageUrl', '/dealerProfile/:dealerId/profile/:profileId/type/:type')
        .constant('profilePageControllerName', 'profileController')
        .constant('profilePageTemplateUrl', 'js/pages/profile-detail/profile-detail-template.html')
        
        .constant('dsmUrl','/dsmSummary/:id')
        .constant('dsmControllerName','dsmSummaryController')
        .constant('dsmTemplateUrl','js/pages/dsm-summary/dsm-summary-template.html')

        .constant('defaultRedirection', '/dsmSummary/2926500');
}());