
var renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.setClearColor("#000000");

document.getElementById('canvas99').appendChild(renderer.domElement);

var camera = new THREE.PerspectiveCamera(50, window.innerWidth / window.innerHeight, 0.1, 1000);
camera.position.set(0, 0, 75);

var controls = new THREE.OrbitControls(camera);
controls.target = new THREE.Vector3(0, 0, 20);
controls.update();

var scene = new THREE.Scene();

var globalMaterial = new THREE.SpriteMaterial({
    map: new THREE.CanvasTexture(generateSprite()),
    blending: THREE.AdditiveBlending
});

var particles = [];
var numOfParticles = 20000;

var sigma = 10;
var rho = 22;
var beta = 8 / 3;

var dt = 0.0085;

for (var i = 0; i < numOfParticles; i++) {

    var material = new THREE.Sprite(globalMaterial);

    var x = void 0,
        y = void 0,
        z = void 0;
    if (i == 0) {
        x = y = z = 0.1;
    } else {
        x = particles[i - 1].material.position.x;
        y = particles[i - 1].material.position.y;
        z = particles[i - 1].material.position.z;

        var dx = sigma * (y - x);
        var dy = x * (rho - z) - y;
        var dz = x * y - beta * z;

        x += dx * dt;
        y += dy * dt;
        z += dz * dt;
    }

    var particle = new Particle(material, x, y, z);
    particle.material.scale.x = .3;
    particle.material.scale.y = .3;
    particle.material.scale.z = .3;

    particle.material.position.x = x;
    particle.material.position.y = y;
    particle.material.position.z = z;

    scene.add(particle.material);
    particles.push(particle);
}

dt = .0005;
function render() {
    renderer.render(scene, camera);

    renderer.autoClearColor = false;
    for (var i = 0; i < numOfParticles; i++) {
        var _x = particles[i].material.position.x;
        var _y = particles[i].material.position.y;
        var _z = particles[i].material.position.z;

        var _dx = sigma * (_y - _x);
        var _dy = _x * (rho - _z) - _y;
        var _dz = _x * _y - beta * _z;

        _x += _dx * dt;
        _y += _dy * dt;
        _z += _dz * dt;

        particles[i].material.position.x = _x;
        particles[i].material.position.y = _y;
        particles[i].material.position.z = _z;
    }

    controls.update();
    requestAnimationFrame(render);
}

render();

// Functions to generate particle and particle sprite

function Particle(material, x, y, z) {
    this.material = material;
    this.material.position.x = x;
    this.material.position.y = y;
    this.material.position.z = z;
}

function generateSprite() {
    var canvas = document.createElement('canvas');
    canvas.width = 12;
    canvas.height = 12;

    var context = canvas.getContext('2d');
    var gradient = context.createRadialGradient(canvas.width / 2, canvas.height / 2, 0, canvas.width / 2, canvas.height / 2, canvas.width / 2);
    gradient.addColorStop(0, 'rgba(255,255,255,1)');
    gradient.addColorStop(0.2, 'rgba(255,50,50,1)');
    gradient.addColorStop(0.4, 'rgba(0,0,64,1)');
    gradient.addColorStop(1, 'rgba(0,0,0,1)');

    context.fillStyle = gradient;
    context.fillRect(0, 0, canvas.width, canvas.height);

    return canvas;
}
$(function () {
    $('canvas').addClass('canvas99')
})
