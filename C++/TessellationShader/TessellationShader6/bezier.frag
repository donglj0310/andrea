// bezier.frag
#version 420

smooth in vec3 fsNormal;

const vec3 LIGHT_VEC = vec3(0, 0, 1.0);

out vec4 gl_FragColor;

void main() {
	float diffuse = max(0.0, dot(LIGHT_VEC, normalize(fsNormal)));
	gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0) * diffuse;
}
