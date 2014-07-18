describe('DealerResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealer');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    describe('get', function() {
        it('returns a promise of a single dealer resource', inject(function(dealerResource, dealerUrl) {
            var dealerId = 111;
	        var type = 'type';

            var dealer = {dealerId: dealerId, type: type};
            var expectedRequest = dealerUrl.replace(':dealerId', dealerId).replace(':type', type);
            var expectedDealer = {name: 'U.T. Motorsports'};

            httpBackend.when('GET', expectedRequest).respond(expectedDealer);
            httpBackend.expectGET(expectedRequest);

            var promise = dealerResource.get(dealer);

            promise.then(function(actualDealer) {
                expect(actualDealer.name).toEqual(expectedDealer.name);
            });

            httpBackend.flush();
        }));
    });
});
