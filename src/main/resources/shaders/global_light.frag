#version 330 core
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform vec4 u_globalLightColor;// RGBA, lower alpha means darker

void main() {
    vec4 texColor = texture2D(u_texture, v_texCoords);
    gl_FragColor = texColor * u_globalLightColor;
}
