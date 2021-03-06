// bezier.vert
// Valutazione di curve spline usando tessellation shader
#version 420

layout(location = 0) in vec3 in_Position;
layout(location = 1) in vec3 in_Normal;

out vec3 tcsNormal;

void main() {
	gl_Position = vec4(in_Position, 1.0);
	tcsNormal = in_Normal;
}