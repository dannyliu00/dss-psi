(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder) {
        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(10)
            .withBootstrap();

        $scope.sumActualValues = function() {
            var total = 0;
            for(var i = 0; i < $scope.orderSegments.length; i++) {
                var os = $scope.orderSegments[i];
                var osActual = parseInt(os.actualQty);
                total = total + osActual;
                sumSegmentTotal(os.segmentName, os.orderSegmentId, osActual);
            }
            $scope.actualGrandTotal = total;
        };

        function sumSegmentTotal(segmentName, osId, newValue) {
            var segment = getSegment(segmentName);
            var total = 0;
            for(var i=0; i < segment.orderSegments.length; i++) {
                var orderSegment = segment.orderSegments[i];
                if(orderSegment.id === osId) {
                    orderSegment.actualQty = newValue;
                    console.log(orderSegment.name);
                    console.log(orderSegment.actualQty);
                }
                total = total + parseInt(orderSegment.actualQty);
            }
            segment.actualQty = total;
        }

        function getSegment(name) {
            for(var i=0; i < $scope.segments.length; i++) {
                if($scope.segments[i].name === name) {
                    return $scope.segments[i];
                }
            }
        }

    }

    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();