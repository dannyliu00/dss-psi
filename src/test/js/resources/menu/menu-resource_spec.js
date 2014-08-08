describe('MenuResource', function () {
    var httpBackend;

    beforeEach(function () {
        angular.mock.module('sellIn.resources.menu');

        angular.mock.inject(function ($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('get', function () {
        it('returns a promise of the menulink based on the user role', inject(function (menuResource, menuUrl) {

            var expectedRequest = menuUrl;
            var expectedMenuLinks = {category: 'data'};

            httpBackend.when('GET', expectedRequest).respond(expectedMenuLinks);
            httpBackend.expectGET(expectedRequest);

            var promise = menuResource.get();

            promise.then(function (menuLinks) {
                expect(menuLinks.data).toEqual(expectedMenuLinks.data);
            });
            httpBackend.flush();
        }));
    });
});