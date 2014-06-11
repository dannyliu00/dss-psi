(function() {
    var mainButton = sellInNamespace('sellIn.directives.mainbutton');

    function MainButtonDirectiveController($scope, $location, $modal, dealerSummaryPageUrl) {
    	
        $scope.buttonCaptionFill = function() {
        	buttonCaption = "";
        	($scope.profile.type === "motorcycle"? buttonCaption = "Auto-fill with Recommendations" : buttonCaption = "Auto-fill with Targets");
        	
        	return buttonCaption;
        };
             
        $scope.autoFill = function(){
        	($scope.profile.type === "motorcycle" ? autoFillVic() : autoFillATV());
        	};
        
        function autoFillVic(){
            for(var i=0; i < $scope.orderSegments.length; i++) {
           	 $scope.orderSegments[i].actual = $scope.orderSegments[i].recommended;
            }
            $scope.sumActualValues();
       }
       
        function autoFillATV() { 
    	   for(var j=0; j < $scope.profile.periods.length; j++) {
               for(var i=0; i < $scope.orderSegments.length; i++) {
                   var orderSegment = $scope.orderSegments[i];
                   orderSegment.quantities[j].actual= orderSegment.quantities[j].recommended;
               		}
               }
           $scope.getActualGrandTotal();
       }
    
        $scope.toSummary = function(dealerId) {
            var changes = $scope.dirtyIndicator;

            switch(changes) {
                case 0:
                    var finalUrl = dealerSummaryPageUrl.replace(':dealerId', dealerId);
                    $location.path(finalUrl);
                    break;
                case 1:
                    var finalUrl = dealerSummaryPageUrl.replace(':dealerId', dealerId);
                    $location.path(finalUrl);
                    break;
                default:
                    openSaveDialog();
                    break;
            }
        };

        function openSaveDialog () {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/modal-template.html',
                controller: 'unsavedChangesController',
                size: 'sm'
//                  resolve: {
//                  items: function (dealerId) {
//                      return dealerId;
//                  }
//              }	
            });

            modalInstance.result.then(function (dealerId) {
                var finalUrl = dealerSummaryPageUrl.replace(':dealerId', $scope.dealer.dealerId);
                $location.path(finalUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }

        $scope.submitRequests = function() {
            if(angular.element('input').hasClass('noncompliant')) {
                $scope.openReasonDialog();
                console.log('this might work');
            }
        };

    }

    mainButton.MainButtonDirectiveController = MainButtonDirectiveController;
})();