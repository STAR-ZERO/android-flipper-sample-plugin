import { FlipperPlugin, FlexColumn, Input, styled, Button, FlexRow } from 'flipper';

const RootColum = styled(FlexColumn)({
  padding: '16px'
})

export default class extends FlipperPlugin {

  static defaultPersistedState = {
    text: ''
  }

  init() {
    this.client.subscribe('send', (params) => {
      this.props.setPersistedState({ text: params.text })
    })
  }

  onClickSend = (e) => {
    this.client.call('send', this.props.persistedState)
  }

  render() {
    return (
      <RootColum>
        <FlexRow>
          <Input
            value={this.props.persistedState.text}
            onChange={(e) => { this.props.setPersistedState({ text: e.target.value }) }}
          />
        </FlexRow>
        <FlexRow>
          <Button onClick={this.onClickSend}>Send</Button>
        </FlexRow>
      </RootColum>
    )
  }
}