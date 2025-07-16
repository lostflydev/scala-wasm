PLUGIN := plugin.wasm
HELLOWORLD := helloworld.wasm
OUT := out.wasm

fetch:
	wkg wit fetch

$(PLUGIN):
	wasm-tools component embed wit \
		plugin/.2.12/target/scala-2.12/example-plugin-module-for-component-model-fastopt/main.wasm \
		-w component:scala/plugin \
		-o $(PLUGIN) --encoding utf16 && \
	wasm-tools component new $(PLUGIN) -o $(PLUGIN)

$(HELLOWORLD):
	wasm-tools component embed wit \
		helloworld/.2.12/target/scala-2.12/example-main-module-for-component-model-fastopt/main.wasm \
		-w component:scala/main \
		-o $(HELLOWORLD) --encoding utf16 && \
	wasm-tools component new $(HELLOWORLD) -o $(HELLOWORLD)

$(OUT): $(PLUGIN) $(HELLOWORLD)
	wac plug --plug $(PLUGIN) $(HELLOWORLD) -o $(OUT)

run: $(OUT)
	wasmtime -W function-references,gc $(OUT)

clean:
	rm -f $(PLUGIN) $(HELLOWORLD) $(OUT)

.PHONY: $(PLUGIN) $(HELLOWORLD) $(OUT) run clean
