/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    describe('DealerProfileSummaryCtrl', function() {
        var scope, routeParams, location, dealerResource, dealerProfilesResource, expectedDealerId, expectedUrl;
        var expectedDealerDeferred, expectedProfilesDeferred;
        var ctrl;

        beforeEach(function () {
            angular.mock.module('sellIn.pages.dealerProfileSummary');

            angular.mock.module(function ($provide) {
                dealerResource = jasmine.createSpyObj('dealerResource', ['get']);
                $provide.decorator('dealerResource', [function () {
                    return dealerResource;
                }]);
                dealerProfilesResource = jasmine.createSpyObj('dealerProfilesResource', ['query']);
                $provide.decorator('dealerProfilesResource', [function () {
                    return dealerProfilesResource;
                }]);
            });

            location = jasmine.createSpyObj('location', ['path']);

            expectedDealerId = 111;
            routeParams = {dealerId: expectedDealerId};
            expectedUrl = '/segment1/:dealerId/segment2/:profileId/segment3/:type';
        });

        beforeEach(inject(function ($q, $rootScope, dealerResource, dealerProfilesResource) {
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);

            expectedProfilesDeferred = $q.defer();
            dealerProfilesResource.query.andReturn(expectedProfilesDeferred.promise);

            scope = {};
        }));

        describe('constructor', function () {
            it('initializes dealer on scope', inject(function (dealerResource, dealerProfilesResource) {
                ctrl = new dealerProfileSummary.DealerProfileSummaryCtrl(scope, routeParams, location, dealerResource, dealerProfilesResource, expectedUrl);
                var expectedDealer = {dealerId: expectedDealerId};
                expect(dealerResource.get).toHaveBeenCalledWith(expectedDealer);
                expect(dealerProfilesResource.query).toHaveBeenCalledWith(expectedDealer);
            }));
        });

    });
})();