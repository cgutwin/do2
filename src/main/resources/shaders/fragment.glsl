#ifdef GL_ES
precision mediump float;
#endif

uniform vec2 u_resolution;// Screen resolution
uniform vec2 u_lightPos;// Light position
uniform float u_lightRadius;// Light radius
uniform vec3 u_lightColor;// Light color

void main() {
    vec2 fragCoord = gl_FragCoord.xy;
    float distance = length(fragCoord - u_lightPos);
    float alpha = 1.0 - smoothstep(0.0, u_lightRadius, distance);
    gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);// Red color for debugging
}
