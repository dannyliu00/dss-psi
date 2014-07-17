describe('MenuResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.menu');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('get', function() {
        it('returns a promise containing the menulinks based on the user role', inject(function(menuResource,menuUrl){
        	
            var expectedRequest = menuUrl;
            var expectedList = [{menuLinks: 'displayName'}];
            
            httpBackend.when('GET', expectedRequest).respond(expectedList);
            httpBackend.expectGET;

            var promise = menuResource.query();

            promise.then(function(menuLinks) {
                expect(menuLinks[0].displayName).toEqual(expectedList[0].displayName);
            });
            httpBackend.flush();
        }));
    });
});