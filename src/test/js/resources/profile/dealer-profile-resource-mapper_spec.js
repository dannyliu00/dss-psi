var dealerProfile = sellInNamespace('sellIn.resources.dealerProfile');

describe('OrderSegmentResourceMapper', function() {
    var restOrderSegment, restSegment, jsOrderSegment;
    var mapper;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealerProfile');

        restOrderSegment = {
            "orderSegmentId": 111,
            "name": "U.T. Motorcycle",
            "actualQty": 4,
            "recommendedQty": 8
        };
        jsOrderSegment = {
            "orderSegmentId": 111,
            "name": "U.T. Motorcycle",
            "actualQty": 4,
            "recommendedQty": 8,
            "segmentName": "U.T. Segment"
        };

        restSegment = {"segmentId": 999,
            "name": "U.T. Segment",
            "actualQty": 0,
            "recommendedQty": 4
        };

        mapper = new dealerProfile.OrderSegmentResourceMapper();
    });

    describe('mapFromRest', function() {
        it('maps a REST-based OrderSegment to a JS OrderSegment', function() {
            var actualObject = mapper.mapFromRest(restOrderSegment, restSegment);

            expect(actualObject.orderSegmentId).toEqual(jsOrderSegment.orderSegmentId);
            expect(actualObject.name).toEqual(jsOrderSegment.name);
            expect(actualObject.actualQty).toEqual(jsOrderSegment.actualQty);
            expect(actualObject.recommendedQty).toEqual(jsOrderSegment.recommendedQty);
            expect(actualObject.segmentName).toEqual(jsOrderSegment.segmentName);

        });
    });

    describe('mapToRest', function() {
        it('maps a JS OrderSegment to a REST-based OrderSegment', function() {
            var actualObject = mapper.mapToRest(jsOrderSegment);

            expect(actualObject.orderSegmentId).toEqual(restOrderSegment.orderSegmentId);
            expect(actualObject.name).toEqual(restOrderSegment.name);
            expect(actualObject.actualQty).toEqual(restOrderSegment.actualQty);
            expect(actualObject.recommendedQty).toEqual(restOrderSegment.recommendedQty);
            expect(actualObject.segmentName).not.toBeDefined();
        });
    });
});