(function() {
    var dsmButton = sellInNamespace('sellIn.directives.dsmbutton');

    function DsmButtonController($scope, $location, $modal, dealerSummaryPageUrl, profilePageUrl) {
    	
        $scope.dsmButtonCaptionFill(function() {
        	dsmButtonCaption = "";
        	if(angular.element('input').hasClass('noncompliant')) {
        		dsmButtonCaption = "Submit for Exception";
        	} else if ($scope.dirtyIndicator > 1) {
        		dsmButtonCaption = "Approve with Changes";
        	} else {
        		dsmButtonCaption = "Approve as Requested";
        	}
        	return  dsmButtonCaption;
        });
        
        
        $scope.dsmToSummary = function(dealerId) {
            var dsmChanges = $scope.dirtyIndicator;

            switch(dsmChanges) {
                case 0:
                case 1:
                    $location.path(dsmUrl);
                    break;
                default:
                    openSaveDialog();
                    break;
            }
        };

        function openSaveDialog () {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/unsaved-changes-modal-template.html',
                controller: 'unsavedChangesController',
                size: 'sm'	
            });

            modalInstance.result.then(function (dealerId) {
                $location.path(dsmUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }

        $scope.approveSubmit = function() {
            if(angular.element('input').hasClass('noncompliant')) {
                openReasonDialog();
            }
        };
        
        $scope.sendBack = function() {
        	openReasonDialog();
        };
        
        function openReasonDialog() {
        	
        	var modalInstance = $modal.open({
				templateUrl: 'js/directives/modal/reason-modal-template.html',
				controller: 'reasonModalController',
				size: 'sm'
			});

        	modalInstance.result.then(function (reasonCommentData) {
                $location.path(dsmUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
		}
    }

    dsmButton.DsmButtonController = DsmButtonController;
})();