(function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    function DefaultPageController($scope,$rootScope, $location, appRoleResource,translationResource, dealerSummaryPageUrl, dsmUrl, dsmRoleId, rsmRoleId,translationPSI) {
        var finalUrl = '/';

        
        appRoleResource.get().then(function(role) {
            $scope.role = role;
        }).then(function() {

            switch ($scope.role.customerClass) {
                case dsmRoleId:
	            case rsmRoleId:
                    var type = getProductType($scope.role);
                    finalUrl = dsmUrl
                        .replace(':type', type)
                        .replace(':status', 'current');
                    break;
                default:
	                var type = getProductType($scope.role);
                    finalUrl = dealerSummaryPageUrl
                        .replace(':type', type)
                        .replace(':status', 'current');
            }
        }).then(function() {
            $location.path(finalUrl);
        });

	    var getProductType = function(role) {
		    if(role.sessionDetail.ATV === 'Y') return '2';
		    if(role.sessionDetail.RGR === 'Y') return '6';
		    if(role.sessionDetail.RZR === 'Y') return 'Z';
		    if(role.sessionDetail.VIC === 'Y') return '5';
		    if(role.sessionDetail.IND === 'Y') return 'F';
		    if(role.sessionDetail.MIL === 'Y') return '9';
		    if(role.sessionDetail.OSP === 'Y') return 'D';
	    };
    }
    
//    DefaultPageController.resolve = {
//    		resourceStrings: function(Phone, $q) {
//    		    // see: https://groups.google.com/forum/?fromgroups=#!topic/angular/DGf7yyD4Oc4
//    		    var deferred = $q.defer();
//
// 			   translationResource.get().then(function(data) {
// 				  deferred.resolve(data);
//			   });
//    		    
//    		   return deferred.promise;
//    		  },
//    		  delay: function($q, $defer) {
//    		    var delay = $q.defer();
//    		    $defer(delay.resolve, 1000);
//    		    return delay.promise;
//    		  }
//    		};

    defaultPage.DefaultPageController = DefaultPageController;
})();
