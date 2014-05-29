(function() {
    var dealerProfile = sellInNamespace('sellIn.resources.dealerProfile');

    function OrderSegmentResourceMapper() {

    }

    OrderSegmentResourceMapper.prototype.mapFromRest = function(restOrderSegment, restSegment) {
        var orderSegment = restOrderSegment;
        orderSegment.segmentName = restSegment.name;

        return orderSegment;
    };

    OrderSegmentResourceMapper.prototype.mapToRest = function(jsOrderSegment) {
        var orderSegment = jsOrderSegment;
        orderSegment.segmentName = undefined;

        return orderSegment;
    };

    dealerProfile.OrderSegmentResourceMapper = OrderSegmentResourceMapper;
})();